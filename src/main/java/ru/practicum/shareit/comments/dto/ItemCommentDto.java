package ru.practicum.shareit.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ItemCommentDto {

    private Long id;
    private String itemName;
    private Long authorId;
    private LocalDateTime timeOfCreation;
}
