package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.RequestItem;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private static final String USER_ID_HEAD = "X-Sharer-User-Id";


    @GetMapping("/{itemId}")
    public ItemBookingDto getItemById(@PathVariable("itemId") Long itemId,
                                      @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping
    public List<ItemBookingDto> getItemsForUser(@RequestHeader(USER_ID_HEAD) Long userId,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        return itemService.getItemsForUser(RequestItem.of(userId, from, size));
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable("itemId") Long itemId,
                              @RequestBody ItemDto item,
                              @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemService.updateItem(item, userId, itemId);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto item,
                           @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemService.addItem(item, userId);
    }

    @DeleteMapping("/{itemId}")
    public ItemDto deleteItem(@PathVariable Long itemId,
                           @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemService.deleteItem(itemId, userId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam("text") String text,
                                @RequestHeader(USER_ID_HEAD) Long userId,
                                @RequestParam(defaultValue = "0") int from,
                                @RequestParam(defaultValue = "10") int size) {
        return itemService.searchItem(RequestItem.of(userId, from, size, text));
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@RequestBody CommentDto comment,
                                 @RequestHeader(USER_ID_HEAD) Long userId,
                                 @PathVariable("itemId") Long itemId) {
        return itemService.addComment(comment, userId, itemId);
    }
}