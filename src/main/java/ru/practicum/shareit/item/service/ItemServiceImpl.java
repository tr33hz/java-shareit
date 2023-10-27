package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import ru.practicum.shareit.item.exceptions.AttempChangeImmutableItemException;
import ru.practicum.shareit.item.exceptions.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Item addNewItem(Integer userId, Item item) {
        User userItem = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("Пользователь не найден"));
        item.setOwnerId(userItem.getId());

        return itemRepository.addNewItem(userId, item);
    }

    @Override
    public Item findItemById(Integer itemId, Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        return itemRepository.findItemById(itemId).orElseThrow(() -> new ItemNotFoundException("Предмет не найден"));
    }

    @Override
    public List<Item> findAllItemsOwner(Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        return itemRepository.getAllItems(userId);
    }

    @Override
    public Item updateItem(Integer itemId, Map<String, Object> fields, Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        Item itemExists = itemRepository.findItemById(itemId).orElseThrow(() -> new ItemNotFoundException("Предмет не найден"));

        if (itemExists.getOwnerId().equals(userId)) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Item.class, key);
                field.setAccessible(true);

                if (key.equals("id") && key.equals("ownerId")) {
                    throw new AttempChangeImmutableItemException("Попытка изменить неизменяемое поле");
                } else {
                    ReflectionUtils.setField(field, itemExists, value);
                }
            });
        } else {
            throw new AttempChangeImmutableItemException("Редактировать предмет может только его владелец"); // 404
        }
        return itemRepository.addNewItem(userId, itemExists);
    }

    @Override
    public List<Item> searchItems(String text, Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (text.isBlank() || text.isEmpty()) {
            return Collections.emptyList();
        }

        return itemRepository.searchItems(text, userId);
    }
}
