package ru.practicum.shareit.support;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T, D> {
	private final ModelMapper modelMapper = new ModelMapper();

	public D convertToDto(T entity, Class<D> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	public T convertToPojo(D dto, Class<T> entityClass) {
		return modelMapper.map(dto, entityClass);
	}
}
