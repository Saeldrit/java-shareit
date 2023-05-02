package ru.practicum.shareit.user.service;

import java.util.List;

public interface UserService<I, T> {
	T create(T owner);

	T update(I id, T owner);

	T updateContent(T owner);

	T searchById(I id);

	List<T> unload();

	T remove(I id);
}
