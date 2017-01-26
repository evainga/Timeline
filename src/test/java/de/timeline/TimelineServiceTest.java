package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.testng.annotations.Test;

public class TimelineServiceTest {

	private TimelineService timelineService = new TimelineService();

	@Test
	public void timelineServiceTest() {
		List<Event> allEvents = timelineService.timelineService();

		assertThat(allEvents, hasSize(greaterThan(0)));
	}

}