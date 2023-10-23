package ru.practicum.shareit.user.util;


public class UserIdGenerator {
    private int idByUser;

    public int getUserId() {
        return idByUser++;
    }
}
