package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@Slf4j
public class UserController {

	private final UserService<Integer, UserDto> uService;

	@PostMapping
	public ResponseEntity<UserDto> requestToAdd(@Valid @RequestBody UserDto userDto) {
		return ResponseEntity.ok(uService.create(userDto));
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserDto> requestForUpdate(@RequestBody UserDto userDto,
													@PathVariable Integer userId) {
		return ResponseEntity.ok(uService.update(userId, userDto));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> requestToView(@PathVariable Integer userId) {
		return ResponseEntity.ok(uService.searchById(userId));
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> requestToUnload() {
		return ResponseEntity.of(ofNullable(uService.unload()));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> requestToRemove(@PathVariable Integer userId) {
		return ResponseEntity.ok(uService.remove(userId));
	}
}
