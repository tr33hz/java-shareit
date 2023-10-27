package ru.practicum.shareit.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.exceptions.UserValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.util.UserIdGenerator;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final Map<Integer, User> userMap = new HashMap<>();
    private final UserIdGenerator userIdGenerator = new UserIdGenerator();


    @Override
    public User addUser(User user) {

        if (userMap.containsValue(user)) {
            save(user);
            log.info("Пользователь с id = {}, успешно обновлен", user.getId());

        } else {
            userEmailValidation(user);
            user.setId(userIdGenerator.getUserId());
            userMap.put(user.getId(), user);

            log.info("Пользователь с id = {}, успешно сохранен", user.getId());
        }
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = userMap.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        if (userMap.isEmpty()) {
            log.info("Список пользователь пуст, возвращен пустой лист");
            return Collections.emptyList();
        }
        log.info("Список всех пользователей возвращен");
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void delete(Integer id) {
        userMap.remove(id);
    }

    public void save(User user) {

        user.setEmail(user.getEmail());
        user.setName(user.getName());
    }


    public void userEmailValidation(User user) {
        Optional<User> validUser = getAll().stream()
                .filter(userValid -> userValid.getEmail().equals(user.getEmail()))
                .findFirst();

        if (validUser.isPresent()) {
            throw new UserValidationException("Пользователь с таким email уже существует");
        }
    }
}