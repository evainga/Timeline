package de.timeline;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
				ZonedDateTime.of(2017, 04, 12, 10, 15, 30, 00, ZoneId.of("Europe/Paris")));
		Event christmas = new Event(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Weihnachten",
				ZonedDateTime.of(2017, 12, 24, 00, 00, 00, 00, ZoneId.of("Europe/Paris")));
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

	public Optional<Event> getEventByUUID(UUID uuid) {
		return eventDb.stream()
				.filter(event -> event.getEventId().equals(uuid))
				.findFirst();
	}

	public boolean deleteEvent(UUID uuid) {
		Optional<Event> eventByUUID = getEventByUUID(uuid);
		if (!eventByUUID.isPresent())
			return false;
		return eventDb.remove(eventByUUID.get());
	}

}