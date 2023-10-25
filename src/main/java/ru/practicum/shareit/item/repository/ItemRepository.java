package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

public interface ItemRepository {
    Item addNewItem(Integer userId, Item item);
}
