package ru.practicum.shareit.user.util;


public class UserIdGenerator {
    private int idByUser = 1;

    public int getUserId() {
        return idByUser++;
    }
}
