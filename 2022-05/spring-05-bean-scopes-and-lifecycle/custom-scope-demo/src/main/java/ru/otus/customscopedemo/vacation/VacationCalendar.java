package ru.otus.customscopedemo.vacation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VacationCalendar {
    private static final Logger logger = LoggerFactory.getLogger(VacationCalendar.class);

    private boolean canTakeVacation = false;

    public boolean isCanTakeVacation() {
        return canTakeVacation;
    }

    public void setCanTakeVacation(boolean canTakeVacation) {
        logger.info("Заявление на отпуск {}", canTakeVacation? "одобрено": "отклонено");
        this.canTakeVacation = canTakeVacation;
    }
}
