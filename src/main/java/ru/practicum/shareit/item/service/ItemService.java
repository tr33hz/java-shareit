package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    Item addNewItem(Integer userId, Item item);

    Item findItemById(Integer itemId, Integer userId);

    List<Item> findAllItemsOwner(Integer userId);
}
