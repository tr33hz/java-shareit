package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User findById(Integer id);

    List<User> getAll();

    User update(User user);

    void delete(long id);
}
