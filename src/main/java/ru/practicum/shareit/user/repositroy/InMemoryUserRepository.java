package ru.practicum.shareit.user.repositroy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.DuplicateEmailException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
@Repository("uMemoryRepository")
public class InMemoryUserRepository implements UserRepository<Integer, User> {

	private final Map<Integer, User> memory = new HashMap<>();
	private final Set<User> hash = new HashSet<>();
	private final Set<String> email = new HashSet<>();
	private int id = 0;

	@Override
	public User create(User owner) {
		if (hash.add(owner)) {
			User userWithId = owner.withId(++id);
			memory.putIfAbsent(userWithId.getId(), userWithId);
			email.add(owner.getEmail());
		} else {
			throw new DuplicateEmailException(
					String.format("ERROR user with such an email '%s' exists", owner.getEmail())
			);
		}
		return memory.get(id);
	}

	@Override
	public User update(Integer id, User owner) {
		return memory.computeIfPresent(id, (k, v) -> {
			String em = isNull(owner.getEmail()) ? v.getEmail() : owner.getEmail();
			if (email.contains(em)) {
				throw new DuplicateEmailException(
						String.format("ERROR user with such an email '%s' exists", em));
			}
			hash.remove(v);
			hash.add(owner);
			return owner.withId(id)
					.withEmail(isNull(owner.getEmail()) ? v.getEmail() : owner.getEmail())
					.withName(isNull(owner.getName()) ? v.getName() : owner.getName());
		});
	}

	@Override
	public User updateContent(User owner) {
		return memory.computeIfPresent(owner.getId(), (k, v) -> v.withItems(owner.getItems()));
	}

	@Override
	public User findById(Integer id) {
		return memory.get(id);
	}

	@Override
	public List<User> unload() {
		return new ArrayList<>(memory.values());
	}

	@Override
	public User remove(Integer id) {
		User user = memory.remove(id);
		return !isNull(user) ?
				(hash.remove(user) && email.remove(user.getEmail())
						? user : null) : null;
	}
}
