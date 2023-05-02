package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.RequestWithoutAccessPermission;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.InMemoryItemRepository;
import ru.practicum.shareit.support.Mapper;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("iService")
public class AppItemService implements ItemService<Integer, ItemDto> {

	private final InMemoryItemRepository itemMemoryRepository;
	private final UserService<Integer, UserDto> uService;
	private final Mapper<Item, ItemDto> mapper;

	@Override
	public ItemDto registerThing(Integer ownerId, ItemDto itemDto) {
		UserDto dto = uService.searchById(ownerId);
		itemDto.setOwner(dto);

		Item itemPojo = itemMemoryRepository.saveThing(
				mapper.convertToPojo(itemDto, Item.class));

		ItemDto itemDto1 = mapper.convertToDto(itemPojo, ItemDto.class);

		dto.addToItems(itemDto1);
		uService.updateContent(dto);
		return itemDto1;
	}

	@Override
	public ItemDto updateItem(Integer ownerId, Integer itemId, ItemDto itemDto) {
		UserDto byId = uService.searchById(ownerId);
		Item item = itemMemoryRepository.searchById(itemId);

		if (item.getOwner().getId().equals(ownerId)) {
			item = itemMemoryRepository.updateItem(itemId,
					mapper.convertToPojo(itemDto, Item.class));
		} else {
			throw new RequestWithoutAccessPermission(String.format("Owner by id '%s' doesn't have access", ownerId));
		}

		ItemDto itemDto1 = mapper.convertToDto(item, ItemDto.class);

		byId.addToItems(itemDto1);
		uService.updateContent(byId);
		return itemDto1;
	}

	@Override
	public ItemDto view(Integer itemId) {
		return mapper.convertToDto(itemMemoryRepository.searchById(itemId), ItemDto.class);
	}

	@Override
	public List<ItemDto> viewAllItems(Integer ownerId) {
		return new ArrayList<>(uService.searchById(ownerId).getItems());
	}

	@Override
	public List<ItemDto> viewItemByKey(String key) {
		return itemMemoryRepository.searchAllItemsByKey(key).stream()
				.map(item -> mapper.convertToDto(item, ItemDto.class))
				.collect(Collectors.toList());
	}
}
