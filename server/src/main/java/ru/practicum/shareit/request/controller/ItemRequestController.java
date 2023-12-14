package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.GetItemRequest;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    private static final String USER_ID_HEAD = "X-Sharer-User-Id";


    @PostMapping
    public ItemRequestDto addItemRequest(@RequestBody ItemRequestDto itemRequest,
                                         @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemRequestService.addItemRequest(itemRequest, userId);
    }

    @GetMapping
    public List<ItemRequestDto> getItemRequestsForUser(@RequestHeader(USER_ID_HEAD) Long userId) {
        return itemRequestService.getItemRequestsForUser(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> getItemRequestsPageable(@RequestHeader(USER_ID_HEAD) Long userId,
                                         @RequestParam(defaultValue = "0") int from,
                                         @RequestParam(defaultValue = "10") int size) {
        return itemRequestService.getItemRequests(GetItemRequest.of(userId, from, size));
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto getItemRequestById(@RequestHeader(USER_ID_HEAD) Long userId,
                                             @PathVariable Long requestId) {
        return itemRequestService.getItemRequestById(requestId, userId);
    }

    @DeleteMapping("/{requestId}")
    public ItemRequestDto deleteItemRequest(@PathVariable("requestId") Long requestId,
                                            @RequestHeader(USER_ID_HEAD) Long userId) {
        return itemRequestService.deleteItemRequest(requestId, userId);
    }
}