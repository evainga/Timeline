package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.testng.annotations.Test;

public class TimelineControllerTest {

	private TimelineController timelineController = new TimelineController();

	@Test
	public void showAllEvents() {
		List<Event> allEvents = timelineController.showAllEvents();
		assertThat(allEvents, not(empty()));
	}

}
