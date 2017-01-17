package de.timeline;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(classes = TimelineApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActuatorControllerIT extends AbstractTestNGSpringContextTests {
	@Value("${local.server.port}")
	int port;

	public RequestSpecification getPlainRequestSpec() {
		return new RequestSpecBuilder().build().baseUri("http://localhost/timeline").port(port);
	}

	@Test
	public void showAllEvents() {
		given(getPlainRequestSpec())
				.when()
				.get("actuator/health")
				.then()
				.statusCode(200)
				.body("status", is("UP"));
	}
}
