package de.timeline;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.testng.annotations.Test;

public class EventTest {
	@Test
	public void allArgsConstructor() {
		Event testEvent = new Event(UUID.fromString("4414177a-8b5b-4e1f-8fe8-eb736f39ce13"), "Weihnachten",
				LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		testEvent.setEventId(UUID.fromString("4414177a-8b5b-4e1f-8fe8-eb736f39ce13"));
		assertThat(testEvent.getEventId(), is(UUID.fromString("4414177a-8b5b-4e1f-8fe8-eb736f39ce13")));
		assertThat(testEvent.getEventName(), is("Weihnachten"));
		assertThat(testEvent.getEventDate(), is(LocalDateTime.of(2016, 12, 24, 18, 0, 0)));
	}

	@Test
	public void requiredArgsConstructor() {
		Event testEvent = new Event("Weihnachten", LocalDateTime.of(2016, 12, 24, 18, 0, 0));
		assertThat(testEvent.getEventId(), is(nullValue()));
		assertThat(testEvent.getEventName(), is("Weihnachten"));
		assertThat(testEvent.getEventDate(), is(LocalDateTime.of(2016, 12, 24, 18, 0, 0)));
	}
}
