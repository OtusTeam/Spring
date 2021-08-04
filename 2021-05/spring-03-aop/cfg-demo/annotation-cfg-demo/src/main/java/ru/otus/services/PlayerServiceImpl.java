package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final IOService ioService;

    public PlayerServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Player getPlayer() {
        ioService.out("Представьтесь пожалуйста");
        String playerName = ioService.readLn("Введите имя: ");
        return new Player(playerName);
    }
}
