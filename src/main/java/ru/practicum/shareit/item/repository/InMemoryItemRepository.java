package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository("inMemoryRepository")
public class InMemoryItemRepository implements ItemRepository<Integer, Item> {

	private final Map<Integer, Item> memory = new HashMap<>();
	private int id = 0;

	@Override
	public Item saveThing(Item item) {
		return memory.computeIfAbsent(++id, integer -> item.withId(id));
	}

	@Override
	public Item updateItem(Integer itemId, Item item) {
		return memory.computeIfPresent(itemId, (k, v) -> v
				.withDescription(item.getDescription() == null
						? v.getDescription() : item.getDescription())
				.withName(item.getName() == null
						? v.getName() : item.getName())
				.withIsAvailable(item.getIsAvailable() == null
						? v.getIsAvailable() : item.getIsAvailable()));
	}

	@Override
	public Item searchById(Integer itemId) {
		return memory.get(itemId);
	}

	@Override
	public List<Item> searchAllItemsByKey(String key) {
		return memory.values()
				.stream()
				.filter(item -> containsWordIgnoreCase(item.getDescription(), key))
				.filter(Item::getIsAvailable)
				.collect(Collectors.toList());
	}

	public boolean containsWordIgnoreCase(String sentence, String word) {
		if (sentence == null || (word == null || word.isEmpty())) {
			return false;
		}
		String sentenceLower = sentence.toLowerCase();
		String wordLower = word.toLowerCase();

		return sentenceLower.contains(wordLower);
	}
}
