package de.timeline;

import java.time.LocalDateTime;

public class Event {

	private String eventName;
	private LocalDateTime eventDate;

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventName() {
		return eventName;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

}
