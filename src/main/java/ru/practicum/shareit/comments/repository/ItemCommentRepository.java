package ru.practicum.shareit.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.comments.model.ItemComment;

public interface ItemCommentRepository extends JpaRepository<ItemComment, Long> {

}
