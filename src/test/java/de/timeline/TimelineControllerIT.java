package de.timeline;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
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
				.then()
				.statusCode(200)
				.body("$", hasSize(greaterThan(0)))
				.body("[0].eventId", both(instanceOf(String.class)).and(not("")))
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
				.statusCode(201)
				.header(HttpHeaders.LOCATION, not(empty()));
	}

	@Test
	public void createNewEventWithInvalidName() {
		given(getPlainRequestSpec())
				.when() // Request
				.body(new Event("Te", LocalDateTime.of(2018, 1, 1, 0, 0)))
				.contentType(ContentType.JSON)
				.post("events")
				.then() // Logging Response
				.statusCode(400)
				.body("errors[0].objectName", is("event"))
				.body("errors[0].field", is("eventName"));
		// .body("errors[0].code", is("NotBlank"));
	}

	@Test
	public void deleteUnknownOldEvent() {
		given(getPlainRequestSpec())
				.when()
				.pathParam("uuid", UUID.randomUUID())
				.delete("events/{uuid}")
				.then()
				.statusCode(404);
	}

	@Test
	public void createNewEventAndDeleteSubsequently() {
		String uuid = given(getPlainRequestSpec())
				.when()
				.body(new Event("Neujahr 2018", LocalDateTime.of(2018, 1, 1, 0, 0)))
				.contentType(ContentType.JSON)
				.post("events")
				.then()
				.statusCode(201)
				.extract().header(HttpHeaders.LOCATION);

		given(getPlainRequestSpec())
				.when()
				.pathParam("uuid", uuid)
				.delete("events/{uuid}")
				.then()
				.statusCode(204);
	}
}
