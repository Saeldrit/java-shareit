package ru.practicum.shareit.user.model;

import lombok.*;
import ru.practicum.shareit.item.model.Item;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Sprint add-controllers.
 */

@With
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "items"})
public class User {
	private Integer id;

	@NotNull
	@Email
	private String email;

	private String name;
	private Set<Item> items = new HashSet<>();

	public void addToItems(Item item) {
		this.items.add(item);
	}
}
