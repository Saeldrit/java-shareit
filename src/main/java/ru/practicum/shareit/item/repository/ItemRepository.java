package ru.practicum.shareit.item.repository;

import java.util.List;

public interface ItemRepository<I, T> {

	T saveThing(T item);

	T updateItem(I itemId, T item);

	T searchById(I itemId);

	List<T> searchAllItemsByKey(String key);
}
