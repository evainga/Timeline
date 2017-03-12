package de.timeline;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(path = "/events/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Event showSpecificEvent(@PathVariable UUID uuid) {
		return timelineService.getEventByUUID(uuid);
	}

	@PostMapping("/events")
	public ResponseEntity<?> createNewEvent(@Valid @RequestBody Event event) {
		UUID uuid = timelineService.createEvent(event);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, uuid.toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping("/events/{uuid}")
	public ResponseEntity<?> deleteOldEvent(@PathVariable UUID uuid) {
		if (timelineService.deleteEvent(uuid)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
