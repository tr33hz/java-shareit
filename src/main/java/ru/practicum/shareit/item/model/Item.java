package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
@AllArgsConstructor
public class Item {

    private Integer id;

    @NotBlank(message = "Название не может быть пустым")
    @NotNull
    private String name;

    @NotBlank(message = "Описание не может пустым")
    @NotNull
    private String description;

    @NotNull(message = "Поле avaible не может быть пустым")
    private Boolean available;

    private User owner;

}
