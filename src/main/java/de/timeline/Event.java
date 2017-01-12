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

	public static void main(String[] args) {
		Event eventExample = new Event();
		eventExample.setName("Weihnachten");
		eventExample.setDate(LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		java.lang.System.out.println(eventExample.eventDate + ": " + eventExample.eventName);
		java.lang.System.out.println(eventExample.getDate() + ": " + eventExample.getName());

	}
}
