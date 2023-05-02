package ru.practicum.shareit.item.service;

import java.util.List;

public interface ItemService<I, T> {

	T registerThing(I ownerId, T item);

	T updateItem(I ownerId, I itemId, T item);

	T view(I itemId);

	List<T> viewAllItems(I ownerId);

	List<T> viewItemByKey(String key);
}
