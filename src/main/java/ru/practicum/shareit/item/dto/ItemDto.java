package ru.practicum.shareit.item.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
public class ItemDto {

    Integer id; //Id вещи.

    @NotBlank(message = "Поле не может быть пустым")
    @NotNull(message = "Поле не может быть пустым")
    String name;

    @NotBlank(message = "Поле не может быть пустым")
    @NotNull(message = "Поле не может быть пустым")
    String description;

    @NotNull(message = "Поле не может быть пустым")
    Boolean available;

    Integer ownerId; //Владелец вещи ownerId == userId.

}

