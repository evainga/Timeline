package de.timeline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

	@Test
	public void createEvent() {
		Event sommer = new Event("Sommeranfang", LocalDate.of(2016, 6, 1).atStartOfDay());
		timelineController.createNewEvent(sommer);
		verify(timelineService).createEvent(sommer);
	}
}
