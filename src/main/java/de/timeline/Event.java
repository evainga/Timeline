package de.timeline;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Event {

	private String eventName;
	private LocalDateTime eventDate;

}
