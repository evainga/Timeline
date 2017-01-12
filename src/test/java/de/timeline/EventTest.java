package de.timeline;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class EventTest {
	private Event testEvent = new Event();

	@Test
	public void testName() {
		testEvent.setName("Weihnachten");
		assertThat(testEvent.getName(), is("Weihnachten"));
	}

	@Test
	public void testDate() {
		testEvent.setDate(LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		assertThat(testEvent.getDate(), is(LocalDateTime.of(2016, 12, 24, 18, 0, 0)));
	}
}
