package de.timeline;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class TimelineService {

	public List<Event> timelineService() {
		Event easter = new Event("Ostern", LocalDate.of(2017, 4, 16).atStartOfDay());
		Event christmas = new Event("Weihnachten", LocalDate.of(2017, 12, 24).atStartOfDay());
		return Lists.newArrayList(easter, christmas);
	}
}