package de.timeline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

@Service
public class TimelineService {
	private List<Event> eventDb = new ArrayList<>();

	@PostConstruct
	@VisibleForTesting
	void setupDb() {
		Event easter = new Event(UUID.fromString("00000000-0000-0000-0000-000000000001"), "Ostern",
				LocalDate.of(2017, 4, 16).atStartOfDay());
		Event christmas = new Event(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Weihnachten",
				LocalDate.of(2017, 12, 24).atStartOfDay());
		eventDb.add(easter);
		eventDb.add(christmas);
	}

	public List<Event> getAllEvents() {
		return eventDb;
	}

	public UUID createEvent(Event newEvent) {
		UUID uuid = UUID.randomUUID();
		newEvent.setEventId(uuid);
		eventDb.add(newEvent);
		return uuid;
	}

	public Event getEventByUUID(UUID uuid) {
		for (Event event : eventDb) {
			if (event.getEventId().equals(uuid)) {
				return event;
			}
		}
		return null;
	}

	public boolean deleteEvent(UUID uuid) {
		return eventDb.remove(getEventByUUID(uuid));
	}

}