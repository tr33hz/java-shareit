package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import ru.practicum.shareit.user.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.exceptions.UserValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void delete(Integer id) {
        userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        userRepository.delete(id);
    }

    @Override
    public User update(Integer id, Map<String, Object> fields) {
        User userExists = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            field.setAccessible(true);
            if (key.equals("email")) {
                if (userEmailCheckForUpdate(id, String.valueOf(value))) {
                    throw new UserValidationException("Email занят");
                }
            }
            ReflectionUtils.setField(field, userExists, value);
        });


        return userRepository.addUser(userExists);
    }

    public boolean userEmailCheckForUpdate(Integer id, String email) {
        for (User user : userRepository.getAll()) {
            if (user.getId() != id) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }
}


