package de.timeline;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;

@Data
public class TestLombokEvent {

	private String lombokEventName;
	private LocalDateTime lombokEventDate;
	@Getter
	private String name;

}
