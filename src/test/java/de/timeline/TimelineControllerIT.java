package de.timeline;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

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
				.body("[0].eventName", both(instanceOf(String.class)).and(not("")))
				.body("[0].eventDate.month", both(instanceOf(String.class)).and(not("")));
	}

	@Test
	public void createNewEvent() {
		given(getPlainRequestSpec())
				.when()
				.body(new Event())
				.contentType(ContentType.JSON)
				.post("events")
				.then()
				.statusCode(200);
		// .header(HttpHeaders.LOCATION, is("http://udjfjnr"));

	}

	@Test
	public void deleteOldEvent() {
		given(getPlainRequestSpec())
				.when()
				.body(new Event())
				.contentType(ContentType.JSON)
				.delete("events")
				.then()
				.statusCode(200);

	}
}
