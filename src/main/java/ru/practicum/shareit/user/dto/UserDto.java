package ru.practicum.shareit.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    Integer id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;

    @NotBlank(message = "Поле не может быть пустым")
    @Email(message = "Неккоректный email")
    String email;

}
