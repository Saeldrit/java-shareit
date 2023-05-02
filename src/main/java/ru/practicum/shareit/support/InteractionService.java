package ru.practicum.shareit.support;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

@Service
@RequiredArgsConstructor
public class InteractionService {

	private final UserService<Integer, UserDto> uService;
//	private final ItemService<Integer, ItemDto> iService;

	public ItemDto saveItemToOwner(Integer ownerId, ItemDto itemDto) {
		UserDto dto = uService.searchById(ownerId);
		dto.addToItems(itemDto);
		itemDto.setOwner(dto);
		uService.updateContent(dto);
		return itemDto;
	}

	public UserDto searchById(Integer ownerId) {
		return uService.searchById(ownerId);
	}
}
