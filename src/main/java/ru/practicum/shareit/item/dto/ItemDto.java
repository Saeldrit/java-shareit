package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemDto {
	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	private String description;

	@NotNull
	@JsonProperty("available")
	private Boolean isAvailable;

	@JsonIgnore
	private UserDto owner;
	private String request;
}
