package ru.practicum.shareit.user.repositroy;

import java.util.List;

public interface UserRepository<I, T> {
	T create(T owner);

	T update(I id, T owner);

	T updateContent(T owner);

	T findById(I id);

	List<T> unload();

	T remove(I id);
}
