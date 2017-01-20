package de.timeline;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class TimelineController {

	@GetMapping(path = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Event> showAllEvents() {
		Event easter = new Event("Ostern", LocalDate.of(2017, 4, 16).atStartOfDay());
		Event christmas = new Event("Weihnachten", LocalDate.of(2017, 12, 24).atStartOfDay());
		return Lists.newArrayList(easter, christmas);
	}

	@PostMapping("/events")
	public void createNewEvent(@RequestBody Event event) {
		System.out.println(event);
	}
}
