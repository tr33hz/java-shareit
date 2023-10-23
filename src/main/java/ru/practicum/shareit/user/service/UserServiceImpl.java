package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.exceptions.UserValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userEmailValidation(user);

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
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    public User userEmailValidation(User user) {
        Optional<User> validUser = userRepository.getAll().stream()
                .filter(userValid -> userValid.getEmail().equals(user.getEmail()))
                .findFirst();

        if (validUser.isPresent()) {
            throw new UserValidationException("Пользователь с таким email уже существует");
        }

        return user;
    }

}
