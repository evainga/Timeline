package de.timeline;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Event {
	@JsonIgnore
	private UUID eventId;
	@NotBlank
	private final String eventName;
	// @Future
	private final LocalDateTime eventDate;
}
