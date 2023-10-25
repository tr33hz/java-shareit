package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User addUser(User user);

    User findById(Integer id);

    List<User> getAll();

    void delete(Integer id);

    User update(Integer id, Map<String, Object> fields);
}
