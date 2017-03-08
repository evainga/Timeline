package de.timeline;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.constraints.Size;

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
	@Size(min = 3, message = "You must use at least 3 characters")
	private final String eventName;
	// @Future
	private final ZonedDateTime eventDate;
}
