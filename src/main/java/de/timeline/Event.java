package de.timeline;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(value = "eventId", allowGetters = true)
public class Event {
	private UUID eventId;
	@Size(min = 3, message = "You must use at least 3 characters")
	private final String eventName;
	@Past
	private final ZonedDateTime eventDate;
}
