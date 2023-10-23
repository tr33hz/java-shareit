package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

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

}
