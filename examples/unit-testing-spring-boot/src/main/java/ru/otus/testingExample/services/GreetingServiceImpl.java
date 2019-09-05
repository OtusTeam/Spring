package ru.otus.testingExample.services;

import org.springframework.stereotype.Service;
import ru.otus.testingExample.dao.GreetingDao;

import static ru.otus.testingExample.services.CountryCodes.*;

@Service
public class GreetingServiceImpl implements GreetingService {
    private final IOService ioService;
    private final GreetingDao greetingDao;

    public GreetingServiceImpl(IOService ioService, GreetingDao greetingDao) {
        this.ioService = ioService;
        this.greetingDao = greetingDao;
    }

    @Override
    public void sayRussianGreeting() {
        ioService.out(greetingDao.findGreetingByCountryCode(COUNTRY_CODE_RU).orElseThrow(() -> new GreetingNotFoundException(COUNTRY_CODE_RU)));
    }

    @Override
    public void sayEnglishGreeting() {
        ioService.out(greetingDao.findGreetingByCountryCode(COUNTRY_CODE_EN).orElseThrow(() -> new GreetingNotFoundException(COUNTRY_CODE_EN)));
    }

    @Override
    public void sayChinaGreeting() {
        ioService.out(greetingDao.findGreetingByCountryCode(COUNTRY_CODE_CN).orElseThrow(() -> new GreetingNotFoundException(COUNTRY_CODE_CN)));
    }
}
