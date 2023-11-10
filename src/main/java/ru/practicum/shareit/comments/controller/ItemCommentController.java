package ru.practicum.shareit.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comments.dto.ItemCommentDto;
import ru.practicum.shareit.comments.service.ItemCommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemCommentController {

    private final ItemCommentService itemCommentService;

    @PostMapping
    public ItemCommentDto addItemRequest(@Valid @RequestBody ItemCommentDto itemRequest,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemCommentService.addItemRequest(itemRequest, userId);
    }

    @PatchMapping("/{id}")
    public ItemCommentDto updateItemRequest(@RequestBody ItemCommentDto itemRequest,
                                            @RequestHeader("X-Sharer-User-Id") Long userId,
                                            @PathVariable Long id) {
        return itemCommentService.updateItemRequest(itemRequest, userId, id);
    }

    @GetMapping
    public List<ItemCommentDto> getItemRequests() {
        return itemCommentService.getItemRequests();
    }

    @GetMapping("/{id}")
    public ItemCommentDto getItemRequest(@PathVariable Long id) {
        return itemCommentService.getItemRequestById(id);
    }

    @DeleteMapping("/id")
    public ItemCommentDto deleteItemRequest(@PathVariable("id") Long id,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemCommentService.deleteItemRequest(id, userId);
    }
}
