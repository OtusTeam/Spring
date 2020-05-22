package ru.otus.smigunov.project.service;

import ru.otus.smigunov.project.controller.dto.UserDto;
import ru.otus.smigunov.project.exceptions.ObjectNotFoundException;

import java.net.UnknownHostException;
import java.util.List;

public interface UserService {
    List<UserDto> save(UserDto userDto) throws ObjectNotFoundException, UnknownHostException;
}
