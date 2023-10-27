package ru.practicum.shareit.item.util;


public class ItemIdGenerator {
    private int idByItem = 1;

    public int getItemId() {
        return idByItem++;
    }
}
