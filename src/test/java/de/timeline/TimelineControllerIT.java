package de.timeline;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(classes = TimelineApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TimelineControllerIT extends AbstractTestNGSpringContextTests {
	@Value("${local.server.port}")
	int port;

	public RequestSpecification getPlainRequestSpec() {
		return new RequestSpecBuilder().build().baseUri("http://localhost/timeline").port(port);
	}

	@Test
	public void showAllEvents() {
		given(getPlainRequestSpec())
				.when()
				.get("events")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(greaterThan(0)))
				.body("[0].eventName", both(instanceOf(String.class)).and(not("")))
				.body("[0].eventDate", both(instanceOf(List.class)).and(not(Lists.newArrayList())));
	}

	@Test
	public void createNewEvent() {
		given(getPlainRequestSpec())
				.when()
				.body(new Event("Neujahr 2018", LocalDateTime.of(2018, 1, 1, 0, 0)))
				.contentType(ContentType.JSON)
				.post("events")
				.then()
				.statusCode(200);
		// .header(HttpHeaders.LOCATION, is("http://udjfjnr"));

	}

	@Test
	public void deleteUnknownOldEvent() {
		given(getPlainRequestSpec())
				.when()
				.body(new Event("nicht vorhandenes Event", LocalDateTime.now()))
				.contentType(ContentType.JSON)
				.delete("events")
				.then()
				.statusCode(404);
	}

	@Test
	public void createNewEventAndDeleteSubsequently() {
	}
}
