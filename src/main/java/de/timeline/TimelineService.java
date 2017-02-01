package de.timeline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

@Service
public class TimelineService {
	private List<Event> eventDb = new ArrayList<>();

	@PostConstruct
	@VisibleForTesting
	void setupDb() {
		Event easter = new Event("Ostern", LocalDate.of(2017, 4, 16).atStartOfDay());
		Event christmas = new Event("Weihnachten", LocalDate.of(2017, 12, 24).atStartOfDay());
		eventDb.add(easter);
		eventDb.add(christmas);
	}

	public List<Event> getAllEvents() {
		return eventDb;
	}

	public void createEvent(Event newEvent) {
		eventDb.add(newEvent);
	}
}