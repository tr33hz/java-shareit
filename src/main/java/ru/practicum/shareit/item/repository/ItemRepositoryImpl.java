package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.util.ItemIdGenerator;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Integer, Item> itemMap = new HashMap<>();
    private ItemIdGenerator itemIdGenerator = new ItemIdGenerator();

    @Override
    public Item addNewItem(Integer userId, Item item) {

        item.setId(itemIdGenerator.getItemId());
        itemMap.put(item.getId(), item);

        log.info("Пользователь с id = {}, успешно сохранен", userId);
        return item;
    }
}
