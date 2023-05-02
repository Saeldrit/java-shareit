package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	private String description;

	@NotNull
	@JsonProperty("available")
	private Boolean isAvailable;

	private User owner;
	private ItemRequest request;
}
