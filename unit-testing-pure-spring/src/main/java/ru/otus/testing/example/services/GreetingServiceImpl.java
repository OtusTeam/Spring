package ru.otus.testing.example.services;

import org.springframework.stereotype.Service;
import ru.otus.testing.example.dao.GreetingDao;

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
        ioService.out(greetingDao.findGreetingByCountryCode(CountryCodes.COUNTRY_CODE_RU).orElseThrow(() -> new GreetingNotFoundException(CountryCodes.COUNTRY_CODE_RU)));
    }

    @Override
    public void sayEnglishGreeting() {
        ioService.out(greetingDao.findGreetingByCountryCode(CountryCodes.COUNTRY_CODE_EN).orElseThrow(() -> new GreetingNotFoundException(CountryCodes.COUNTRY_CODE_EN)));
    }

    @Override
    public void sayChinaGreeting() {
        ioService.out(greetingDao.findGreetingByCountryCode(CountryCodes.COUNTRY_CODE_CN).orElseThrow(() -> new GreetingNotFoundException(CountryCodes.COUNTRY_CODE_CN)));
    }
}
