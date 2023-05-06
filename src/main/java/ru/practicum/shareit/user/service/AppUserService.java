package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.support.Mapper;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repositroy.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@Service("userService")
public class AppUserService implements UserService<Integer, UserDto> {

	private final UserRepository<Integer, User> uMemoryRepository;
	private final Mapper<User, UserDto> mapper;

	@Override
	public UserDto create(UserDto owner) {
		User user = uMemoryRepository.create(mapper.convertToPojo(owner, User.class));
		return mapper.convertToDto(user, UserDto.class);
	}

	@Override
	public UserDto update(Integer id, UserDto owner) {
		User user = uMemoryRepository.update(id, mapper.convertToPojo(owner, User.class));
		return mapper.convertToDto(user, UserDto.class);
	}

	@Override
	public UserDto updateContent(UserDto userDto) {
		User user = uMemoryRepository.updateContent(mapper.convertToPojo(userDto, User.class));
		return mapper.convertToDto(user, UserDto.class);
	}

	@Override
	public UserDto searchById(Integer id) {
		User byId = uMemoryRepository.findById(id);
		if (isNull(byId)) {
			throw new EntityNotFoundException(String.format("Owner by id '%d' not found", id));
		}
		return mapper.convertToDto(byId, UserDto.class);
	}

	@Override
	public List<UserDto> unload() {
		return uMemoryRepository.unload().stream()
				.map(user -> mapper.convertToDto(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto remove(Integer id) {
		User remove = uMemoryRepository.remove(id);
		if (isNull(remove)) {
			throw new EntityNotFoundException(String.format("Owner by id '%d' not found", id));
		}
		return mapper.convertToDto(remove, UserDto.class);
	}
}
