package ru.practicum.shareit.comments.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemCommentNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Item comment with id: %d not found";

    public ItemCommentNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
        log.info(String.format(MESSAGE, id));
    }
}
