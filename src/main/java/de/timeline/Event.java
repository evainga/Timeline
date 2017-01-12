package de.timeline;

import java.time.LocalDateTime;

public class Event {

	String eventName;
	LocalDateTime eventDate;

	public void setName(String name) {
		eventName = name;
	}

	public void setDate(LocalDateTime date) {
		eventDate = date;
	}

	public String getName() {
		return this.eventName;
	}

	public LocalDateTime getDate() {
		return this.eventDate;
	}
}
