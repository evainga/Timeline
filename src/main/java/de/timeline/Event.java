package de.timeline;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private final String eventName;
	private final LocalDateTime eventDate;
}
