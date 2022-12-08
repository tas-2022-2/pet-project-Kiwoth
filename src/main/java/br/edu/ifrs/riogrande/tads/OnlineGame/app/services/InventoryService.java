package br.edu.ifrs.riogrande.tads.OnlineGame.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.IllegalItemSaleException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException.SlotNotFoundException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Inventory;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Slot;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item.Item;

@Service
public class InventoryService {

    public Map<String, Object> sellItem(Slot s) {
        Map<String, Object> out = new HashMap<>();
        int itemQuant = s.getQuantity();
        Item item = s.getItem();
        int value = item.getValue();
        out.put("itemQuant", itemQuant);
        out.put("itemName", item.getName());
        out.put("value", value);
        out.put("total", value * itemQuant);
        s.setItem(null);
        s.setQuantity(0);
        return out;
    }

    public Slot getSlot(Inventory i, int id) {
        Slot slot = i.getSlots().stream().filter(s -> s.getId() == id).findFirst()
                .orElseThrow(() -> new SlotNotFoundException("slot %d not found", id));
        if (slot.getItem() == null) {
            throw new IllegalItemSaleException("slot %d is empty", id);
        }
        return slot;
    }

    public Map<String, Object> addReward(Inventory inventory, Item reward, int quantity) {
        String message;
        Map<String, Object> out = new HashMap<>();
        if (quantity == 0) {
            message = ", and had nothing to store";
        } else {
            boolean isFull = false;
            List<Slot> slots = getSlotsFreeOrStakable(inventory.getSlots(), reward);
            if (slots.size() > 0) {
                while (quantity > 0 && !isFull) {
                    quantity = storeItemToSlotsList(slots, reward, quantity);
                    slots = getSlotsFreeOrStakable(inventory.getSlots(), reward);
                    if (slots.size() == 0) {
                        isFull = true;
                    }
                }
                if (isFull) {
                    message = String.format(
                            ", you couldn't fit everything in your inventory, %d %s have been discarded",
                            quantity, reward.getName());
                } else {
                    message = ", and stored it in your inventory";
                }
            } else {
                message = ", but, your inventory was full.";
            }
        }
        out.put("message", message);
        out.put("discarded", quantity);
        return out;
    }

    private List<Slot> getSlotsFreeOrStakable(List<Slot> slots, Item i) {
        List<Slot> outSlots = List.of();
        List<Slot> equalItemSlot = slots.stream()
                .filter(s -> (s.getItem() == i && s.getQuantity() < i.getMaxStack())).collect(Collectors.toList());
        if (equalItemSlot.size() > 0) {
            outSlots = equalItemSlot;

        } else {
            List<Slot> freeItemSlot = slots.stream().filter(s -> (s.getItem() == null)).collect(Collectors.toList());
            if (freeItemSlot.size() > 0) {
                outSlots = freeItemSlot;
            }
        }
        return outSlots;
    }

    private int storeItemToSlotsList(List<Slot> ss, Item i, int q) {
        for (Slot slot : ss) {
            int slotQuant = getSlotQuantityLeft(slot, i);
            int howMuchToStore = Math.min(q, slotQuant);
            if (q > howMuchToStore) {
                q -= howMuchToStore;
                slot.setItem(i);
                slot.setQuantity(slot.getQuantity() + howMuchToStore);
            } else {
                slot.setItem(i);
                slot.setQuantity(slot.getQuantity() + q);
                return 0;
            }
        }
        return q;
    }

    private int getSlotQuantityLeft(Slot s, Item i) {
        return i.getMaxStack() - s.getQuantity();
    }
}
