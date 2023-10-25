package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.exceptions.UserNotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Item addNewItem(Integer userId, Item item) {
        User userItem = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));//exceptHandler, новое исключение
        item.setOwner(userItem);

        return itemRepository.addNewItem(userId, item);
    }
}
