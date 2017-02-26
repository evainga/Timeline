package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimelineServiceTest {
	private TimelineService timelineService = new TimelineService();

	@BeforeMethod
	private void setupDb() {
		timelineService.setupDb();
	}

	@Test
	public void getAllEvents() {
		List<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents, not(empty()));
	}

	@Test
	public void getFirstEvent() {
		List<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents.get(0).getEventId(), instanceOf(UUID.class));
		assertThat(allEvents.get(0).getEventName(), instanceOf(String.class));
		assertThat(allEvents.get(0).getEventDate(), instanceOf(LocalDateTime.class));
	}

	@Test
	public void createEvent() {
		Event createEvent = new Event(UUID.randomUUID(), "dummy name", LocalDateTime.now());
		timelineService.createEvent(createEvent);
		List<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents, hasItem(createEvent));
	}

	@Test
	public void getEventByUUID() {
		Event eventUUID = new Event(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Weihnachten",
				LocalDate.of(2017, 12, 24).atStartOfDay());
		assertThat(timelineService.getEventByUUID(UUID.fromString("00000000-0000-0000-0000-000000000002")),
				is((eventUUID)));
		assertThat(timelineService.getEventByUUID(UUID.fromString("00000000-0000-0000-0000-000000000404")),
				is(nullValue()));
	}

	@Test
	public void deleteExistingEvent() {
		Event toDelete = new Event(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Weihnachten",
				LocalDate.of(2017, 12, 24).atStartOfDay());
		List<Event> allEvents = timelineService.getAllEvents();
		timelineService.deleteEvent(UUID.fromString("00000000-0000-0000-0000-000000000002"));
		assertThat(allEvents, hasItem(not(toDelete)));
	}

	@Test
	public void deleteNonExistingEvent() {
		Event toDelete = new Event(UUID.fromString("00000000-0000-0000-0000-000000000008"), "Weihnachten",
				LocalDate.of(2017, 12, 24).atStartOfDay());
		List<Event> allEvents = timelineService.getAllEvents();
		timelineService.deleteEvent(UUID.fromString("00000000-0000-0000-0000-000000000008"));
		assertThat(allEvents, hasItem(not(toDelete)));
	}

}