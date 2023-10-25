package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * TODO Sprint add-controllers.
 */

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Item add(@RequestHeader("X-Sharer-User-Id") Integer userId,
                    @Valid @RequestBody Item item) {
        return itemService.addNewItem(userId, item);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Integer itemId,
                            @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.findItemById(itemId, userId);
    }

    @GetMapping
    public List<Item> getAllItems(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.findAllItemsOwner(userId);
    }

    @PatchMapping("/{itemId}")
    public Item updateItem(@RequestBody Map<String, Object> fields,
                           @PathVariable Integer itemId,
                           @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.updateItem(itemId, fields, userId);
    }

    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam(name = "text") String text,
                                  @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.searchItems(text, userId);
    }
}
