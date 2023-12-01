package ru.practicum.shareit.comments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.exception.NotOwnerException;
import ru.practicum.shareit.comments.dto.ItemCommentDto;
import ru.practicum.shareit.comments.exception.ItemCommentNotFoundException;
import ru.practicum.shareit.comments.model.ItemComment;
import ru.practicum.shareit.comments.repository.ItemCommentRepository;
import ru.practicum.shareit.user.exception.UserNotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

import static ru.practicum.shareit.comments.dto.ItemCommentDtoMapper.toDto;
import static ru.practicum.shareit.comments.dto.ItemCommentDtoMapper.toItemRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemCommentServiceImpl implements ItemCommentService {

    private final ItemCommentRepository itemCommentRepository;
    private final UserRepository userRepository;

    @Override
    public ItemCommentDto addItemRequest(ItemCommentDto itemCommentDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        ItemComment itemComment = itemCommentRepository.save(toItemRequest(itemCommentDto, user));
        log.info("Item comment with id: " + itemComment.getItemRequestId() + " saved");

        return toDto(itemComment);
    }

    @Override
    public ItemCommentDto updateItemRequest(ItemCommentDto itemCommentDto, Long userId, Long itemRequestId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        ItemComment itemComment = itemCommentRepository.findById(itemRequestId)
                .orElseThrow(() -> new ItemCommentNotFoundException(itemRequestId));

        if (!itemComment.getAuthor().getUserId().equals(user.getUserId())) {
            throw new NotOwnerException("User with id: " + user.getUserId() + " is not the owner Item comment with id: " + itemRequestId);
        }

        itemCommentDto.setId(itemRequestId);
        ItemComment updatedItemComment = itemCommentRepository.save(toItemRequest(itemCommentDto, user));
        log.info("Item comment with id: " + itemRequestId + " updated");

        return toDto(updatedItemComment);
    }

    @Override
    public List<ItemCommentDto> getItemRequests() {
        return toDto(itemCommentRepository.findAll());
    }

    @Override
    public ItemCommentDto getItemRequestById(Long id) {
        ItemComment itemComment = itemCommentRepository.findById(id)
                .orElseThrow(() -> new ItemCommentNotFoundException(id));

        log.info("Item comment with id: " + id + ": " + itemComment);

        return toDto(itemComment);
    }

    @Override
    public ItemCommentDto deleteItemRequest(Long id, Long userId) {
        ItemComment itemComment = itemCommentRepository.findById(id)
                .orElseThrow(() -> new ItemCommentNotFoundException(id));

        log.info("Item comment with id: " + id + " deleted");

        return toDto(itemComment);
    }
}
