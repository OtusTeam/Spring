package ru.otus.customscopedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.customscopedemo.scope.VacationDoesNotAvailableException;
import ru.otus.customscopedemo.vacation.Vacation;
import ru.otus.customscopedemo.vacation.VacationCalendar;

@SpringBootApplication
public class CustomScopeDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(CustomScopeDemoApplication.class);

	public static void main(String[] args) {
		var ctx = SpringApplication.run(CustomScopeDemoApplication.class, args);
		var vacationCalendar = ctx.getBean(VacationCalendar.class);
		var vacation = ctx.getBean(Vacation.class);

		vacationCalendar.setCanTakeVacation(true);
		try {
			vacation.enjoy();
		} catch (VacationDoesNotAvailableException e) {
			logger.info("Извини Добби, твой отпуск в другом замке!");
		}
	}

}
