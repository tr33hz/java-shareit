package ru.practicum.shareit.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
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
        user.setId(userIdGenerator.getUserId());
        userMap.put(user.getId(), user);

        log.info("Пользователь с id = {}, успешно сохранен", user.getId());
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
}
