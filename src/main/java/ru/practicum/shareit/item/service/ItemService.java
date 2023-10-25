package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    Item addNewItem(Integer userId, Item item);

    Item findItemById(Integer itemId, Integer userId);

    List<Item> findAllItemsOwner(Integer userId);

    Item updateItem(Integer itemId, Map<String, Object> fields, Integer userId);
}
