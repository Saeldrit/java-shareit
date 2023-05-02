package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer id;

	@NotNull
	@Email
	private String email;

	private String name;
	private Set<ItemDto> items = new HashSet<>();

	public void addToItems(ItemDto item) {
		this.items.remove(item);
		this.items.add(item);
	}
}
