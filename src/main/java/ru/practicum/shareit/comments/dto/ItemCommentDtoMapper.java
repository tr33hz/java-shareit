package ru.practicum.shareit.comments.dto;

import ru.practicum.shareit.comments.model.ItemComment;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemCommentDtoMapper {

    public static ItemCommentDto toDto(ItemComment itemComment) {
        Long id = itemComment.getItemRequestId();
        String name = itemComment.getItemName();
        Long authorId = itemComment.getAuthor().getUserId();
        LocalDateTime timeOfCreation = itemComment.getTimeOfCreation();

        return new ItemCommentDto(id, name, authorId, timeOfCreation);
    }

    public static ItemComment toItemRequest(ItemCommentDto itemCommentDto, User author) {
        Long id = itemCommentDto.getId();
        String name = itemCommentDto.getItemName();
        LocalDateTime timeOfCreation = itemCommentDto.getTimeOfCreation();

        return new ItemComment(id, name, author, timeOfCreation);
    }

    public static List<ItemCommentDto> toDto(List<ItemComment> itemComments) {
        List<ItemCommentDto> itemCommentDtos = new ArrayList<>();

        for (ItemComment itemComment : itemComments) {
            itemCommentDtos.add(toDto(itemComment));
        }

        return itemCommentDtos;
    }
}
