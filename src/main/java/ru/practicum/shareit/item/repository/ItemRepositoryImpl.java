package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.util.ItemIdGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Integer, Item> itemMap = new HashMap<>();
    private final ItemIdGenerator itemIdGenerator = new ItemIdGenerator();

    @Override
    public Item addNewItem(Integer userId, Item item) {

        if (itemMap.containsValue(item)) {
            save(item);
            log.info("Предмет с id = {}, успешно обновлен", item.getId());
        } else {
            item.setId(itemIdGenerator.getItemId());
            itemMap.put(item.getId(), item);

            log.info("Предмет с id = {}, успешно сохранен", userId);
        }
        return item;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        return Optional.ofNullable(itemMap.get(itemId));
    }

    @Override
    public List<Item> getAllItems(Integer userId) {
        return itemMap.values().stream()
                .filter(item -> item.getOwnerId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItems(String text, Integer userId) {
        return itemMap.values().stream()
                .filter(item -> (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                        item.getAvailable())
                .collect(Collectors.toList());
    }

    private void save(Item item) {
        item.setName(item.getName());
        item.setDescription(item.getDescription());
        item.setAvailable(item.getAvailable());
    }
}
