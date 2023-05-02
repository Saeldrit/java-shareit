package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.AppItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

	private final AppItemService iService;

	@PostMapping
	public ResponseEntity<ItemDto> requestToAdd(@RequestHeader("X-Sharer-User-Id") Integer ownerId,
												@Valid @RequestBody ItemDto itemDto) {
		return ResponseEntity.ok(iService.registerThing(ownerId, itemDto));
	}

	@PatchMapping("/{itemId}")
	public ResponseEntity<ItemDto> requestForUpdate(@RequestHeader("X-Sharer-User-Id") Integer ownerId,
													@PathVariable Integer itemId,
													@RequestBody ItemDto itemDto) {
		return ResponseEntity.ok(iService.updateItem(ownerId, itemId, itemDto));
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ItemDto> requestToView(@PathVariable Integer itemId) {
		return ResponseEntity.ok(iService.view(itemId));
	}

	@GetMapping
	public ResponseEntity<List<ItemDto>> requestToViewAllThings(
			@RequestHeader("X-Sharer-User-Id") Integer ownerId) {
		return ResponseEntity.ok(iService.viewAllItems(ownerId));
	}

	@GetMapping("/search")
	public ResponseEntity<List<ItemDto>> requestToSearchForItemByKey(@RequestParam String text) {
		return ResponseEntity.ok(iService.viewItemByKey(text));
	}
}
