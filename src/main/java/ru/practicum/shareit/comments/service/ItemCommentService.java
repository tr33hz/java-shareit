package ru.practicum.shareit.comments.service;

import ru.practicum.shareit.comments.dto.ItemCommentDto;

import java.util.List;

public interface ItemCommentService {

    ItemCommentDto addItemRequest(ItemCommentDto itemRequest, Long userId);

    ItemCommentDto updateItemRequest(ItemCommentDto itemRequest, Long userId, Long itemRequestId);

    List<ItemCommentDto> getItemRequests();

    ItemCommentDto getItemRequestById(Long id);

    ItemCommentDto deleteItemRequest(Long id, Long userId);
}
