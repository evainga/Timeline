package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.util.Collection;
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
		Collection<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents, not(empty()));
	}

	@Test
	public void getFirstEvent() {
		Collection<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents.get(0).getEventId(), instanceOf(UUID.class));
		assertThat(allEvents.get(0).getEventName(), instanceOf(String.class));
		assertThat(allEvents.get(0).getEventDate(), instanceOf(LocalDateTime.class));
	}

	@Test
	public void createEvent() {
		Event createEvent = new Event();
		timelineService.createEvent(createEvent);
		Collection<Event> allEvents = timelineService.getAllEvents();
		assertThat(allEvents, hasItem(createEvent));
	}

	@Test
	public void deleteExistingEvent() {
		Event toDelete = new Event();
		Collection<Event> allEvents = timelineService.getAllEvents();
		timelineService.createEvent(toDelete);
		timelineService.deleteEvent(toDelete);
		assertThat(allEvents, hasItem(not(toDelete)));
	}

	@Test
	public void deleteNonExistingEvent() {
		Event toDelete = new Event();
		Collection<Event> allEvents = timelineService.getAllEvents();
		timelineService.deleteEvent(toDelete);
		assertThat(allEvents, hasItem(not(toDelete)));
	}

}