package de.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimelineController {
	@Autowired
	private TimelineService timelineService;

	@GetMapping(path = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Event> showAllEvents() {
		return timelineService.getAllEvents();
	}

	@PostMapping("/events")
	public void createNewEvent(@RequestBody Event event) {
		timelineService.createEvent(event);
	}

	@DeleteMapping("/events")
	public void deleteOldEvent(@RequestBody Event event) {
		timelineService.deleteEvent(event);
	}
}
