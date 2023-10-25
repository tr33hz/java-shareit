package ru.practicum.shareit.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    Integer id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;

    @NotBlank(message = "Поле не может быть пустым")
    @Email(message = "Неккоректный email")
    String email;

}
