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

    private final static String USER_HEAD = "X-Sharer-User-Id";

    @PostMapping
    public Item add(@RequestHeader(USER_HEAD) Integer userId, @Valid @RequestBody Item item) {
        log.info("Поступил запрос на добавление нового предмета с телом = {}, владельцу = {}", item, userId);
        return itemService.addNewItem(userId, item);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Integer itemId,
                            @RequestHeader(USER_HEAD) Integer userId) {
        log.info("Поступил запрос на получение предмета с id = {}, владельца = {}", itemId, userId);
        return itemService.findItemById(itemId, userId);
    }

    @GetMapping
    public List<Item> getAllItems(@RequestHeader(USER_HEAD) Integer userId) {
        log.info("Поступил запрос на получение всех предметов владельца = {}", userId);
        return itemService.findAllItemsOwner(userId);
    }

    @PatchMapping("/{itemId}")
    public Item updateItem(@RequestBody Map<String, Object> fields,
                           @PathVariable Integer itemId,
                           @RequestHeader(USER_HEAD) Integer userId) {
        log.info("Поступил запрос на обновление полей = {}, предмета = {} владельца = {}", fields, itemId, userId);
        return itemService.updateItem(itemId, fields, userId);
    }

    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam(name = "text") String text,
                                  @RequestHeader(USER_HEAD) Integer userId) {
        log.info("Поступил запрос для поиска всех предметов с ключевым словом = {}, от пользователя = {}", text, userId);
        return itemService.searchItems(text, userId);
    }
}
