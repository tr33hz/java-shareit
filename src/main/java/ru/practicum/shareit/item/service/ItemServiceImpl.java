package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.exceptions.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Item addNewItem(Integer userId, Item item) {
        User userItem = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));//exceptHandler, новое исключение
        item.setOwnerId(userItem.getId());

        return itemRepository.addNewItem(userId, item);
    }

    @Override
    public Item findItemById(Integer itemId, Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        return itemRepository.findItemById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Предмет не найден"));
    }

    @Override
    public List<Item> findAllItemsOwner(Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        return itemRepository.getAllItems(userId);
    }
}
