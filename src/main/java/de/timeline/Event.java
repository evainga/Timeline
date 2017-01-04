package de.timeline;

import java.time.LocalDateTime;

public class Event {

	String eventName;
	LocalDateTime eventDate;

	public void setEvent(final String name, final LocalDateTime date) {
		this.eventName = name;
		this.eventDate = date;
	}

	public static void main(String[] args) {
		Event eventExample = new Event();
		eventExample.setEvent("Weihnachten", LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		java.lang.System.out.println(eventExample.eventDate + ": " + eventExample.eventName);
	}
}

// test comment