package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public User add(@Valid @RequestBody User user) {
        log.info("Поступил запрос на добавление пользователя с телом = {}", user);
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("Поступил запрос на получение пользователя с id = {}", id);
        return userService.findById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Поступил запрос на получение всех пользователей");
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        log.info("Поступил запрос на удаление пользователя по id = {}", id);
        userService.delete(id);
        log.info("Пользователь с id = {}, успешно удален", id);
    }

    @PatchMapping("/{id}") /*https://www.youtube.com/watch?v=6AJh4cJNOn0*/
    public User updateUser(@PathVariable Integer id,
                           @RequestBody Map<String, Object> fields) {
        log.info("Поступил запрос для обновления пользователя с id = {}", id);
        return userService.update(id, fields);
    }

}
