package br.edu.ifrs.riogrande.tads.OnlineGame;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.IllegalGameJobChangeException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.UserAlreadyExistsException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.User;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.UserRepository;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.GameJobService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.InventoryService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.UserService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.UserRequest;

@SpringBootTest
class UserServiceTest {

	// SUT
	UserService uService;

	@Mock
	UserRepository uRepository;
	@Mock
	GameJobService gJobService;
	@Mock
	InventoryService invService;
	@Mock
	User u;
	@Mock
	GameJob gj;

	UserRequest uRequestDummy;

	InOrder inOrder;

	@BeforeEach
	void init() {
		inOrder = Mockito.inOrder(uRepository);
		uRequestDummy = new UserRequest();
		uService = new UserService(uRepository, gJobService, invService);
	}

	@Test
	void testUserAlreadyExistsException() {
		given(uRepository.findByUsername(eq("marcio"))).willReturn(Optional.of(u));

		uRequestDummy.setPassword("123");
		uRequestDummy.setUsername("marcio");

		Assertions.assertThatThrownBy(() -> uService.save(uRequestDummy)).isInstanceOf(UserAlreadyExistsException.class)
				.hasMessage("user marcio already exists");

		then(uRepository).should().findByUsername(eq("marcio"));
		then(uRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	void testValidUser() {
		uRequestDummy.setPassword("123");
		uRequestDummy.setUsername("marcio");
		Assertions.assertThatCode(() -> uService.save(uRequestDummy)).doesNotThrowAnyException();

		then(uRepository).should(inOrder).findByUsername(eq("marcio"));
		then(uRepository).should(inOrder).save(any(User.class));
	}

	@Test
	void testList() {
		given(uRepository.findAll()).willReturn(List.of(u));

		assertTrue(!uService.list().isEmpty());
	}

	@Test
	void testWork() {
		given(uRepository.findByUsername(eq("marcio"))).willReturn(Optional.of(u));
		given(gJobService.find(eq("iron_mining"))).willReturn(gj);

		assertTrue(uService.work("marcio", "iron_mining").get("newJob") == "iron_mining");

		then(u).should().setJob(eq(gj));
	}

	@Test
	void testIllegalGameJobChangeException() {
		given(uRepository.findByUsername(eq("marcio"))).willReturn(Optional.of(u));
		given(gJobService.find(eq("iron_mining"))).willReturn(gj);
		given(u.getJob()).willReturn(gj);
		given(gj.getName()).willReturn("iron_mining");

		Assertions.assertThatThrownBy(() -> uService.work("marcio", "iron_mining"))
				.isInstanceOf(IllegalGameJobChangeException.class)
				.hasMessage("user is already working with iron_mining");

		then(uRepository).should().findByUsername(eq("marcio"));
		then(uRepository).shouldHaveNoMoreInteractions();
		then(gJobService).should().find(eq("iron_mining"));
		then(gJobService).shouldHaveNoMoreInteractions();
	}

}