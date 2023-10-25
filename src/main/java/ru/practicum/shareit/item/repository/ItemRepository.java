package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item addNewItem(Integer userId, Item item);

    Optional<Item> findItemById(Integer itemId);

    List<Item> getAllItems(Integer userId);
}
