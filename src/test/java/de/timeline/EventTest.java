package de.timeline;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.testng.annotations.Test;

public class EventTest {
	private Event testEvent = new Event();

	@Test
	public void testName() {
		testEvent.setEventName("Weihnachten");
		assertThat(testEvent.getEventName(), is("Weihnachten"));
	}

	@Test
	public void testDate() {
		testEvent.setEventDate(LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		assertThat(testEvent.getEventDate(), is(LocalDateTime.of(2016, 12, 24, 18, 0, 0)));
	}
}
