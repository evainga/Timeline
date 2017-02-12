package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
		assertThat(allEvents.get(0).getEventName(), instanceOf(String.class));
		assertThat(allEvents.get(0).getEventDate(), instanceOf(LocalDateTime.class));
	}

	@Test
	public void createEvent() {
		Event sommer = new Event("Sommeranfang", LocalDate.of(2016, 6, 1).atStartOfDay());
		timelineService.createEvent(sommer);
	}

	@Test
	public void deleteEvent() {
		Event winter = new Event("Winteranfang", LocalDate.of(2016, 12, 21).atStartOfDay());
		timelineService.createEvent(winter);
		timelineService.deleteEvent(winter);
	}

}