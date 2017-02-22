package de.timeline;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

@Service
public class TimelineService {
	private Map<UUID, Event> eventDb = new HashMap<UUID, Event>();

	@PostConstruct
	@VisibleForTesting
	void setupDb() {
		{
			eventDb.put(UUID.fromString("00000000-0000-0000-0000-000000000001"),
					new Event(UUID.fromString("00000000-0000-0000-0000-000000000001"), "Ostern",
							LocalDate.of(2017, 4, 16).atStartOfDay()));
			eventDb.put(UUID.fromString("00000000-0000-0000-0000-000000000002"),
					new Event(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Weihnachten",
							LocalDate.of(2017, 12, 24).atStartOfDay()));
		}
	}

	public Collection<Event> getAllEvents() {
		return eventDb.values();
	}

	public void createEvent(Event newEvent) {
		eventDb.put(UUID.randomUUID(), newEvent);
	}

	public boolean deleteEvent(UUID uuid) {
		return eventDb.remove(uuid) != null;
	}

}