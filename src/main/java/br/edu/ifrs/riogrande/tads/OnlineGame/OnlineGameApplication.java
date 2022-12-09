package br.edu.ifrs.riogrande.tads.OnlineGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineGameApplication.class, args);
	}

	// public void setTimer(LocalTime time, double timerTemp) {
	// Thread timer = new Thread(() -> {
	// try {
	// Duration duration = Duration.between(LocalTime.now(), time);
	// System.out.println("\n\nSetting a timer for " + time.toString() + " with
	// desired temperature " + timerTemp);

	// Thread.sleep(duration.toMillis());

	// BackendController.desiredTemp = timerTemp;
	// System.out.println("\n\nSet desired temperature " +
	// BackendController.desiredTemp + " with timer trigger.");
	// } catch (InterruptedException e) {
	// System.out.println(e);
	// }
	// });
	// timer.start();
	// }

}
