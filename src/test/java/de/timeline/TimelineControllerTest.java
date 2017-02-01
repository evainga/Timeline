package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

public class TimelineControllerTest extends MockitoTest {
	@InjectMocks
	private TimelineController timelineController;
	@Mock
	private TimelineService timelineService;

	@Test
	public void showAllEvents() {
		// given
		when(timelineService.getAllEvents()).thenReturn(Lists.newArrayList(new Event()));

		// when
		List<Event> allEvents = timelineController.showAllEvents();

		// then
		assertThat(allEvents, not(empty()));
	}

}
