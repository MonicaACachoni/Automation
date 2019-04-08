package rest;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtil {

	private static Logger LOGGER = Logger.getLogger(RestUtil.class);

	public static String postMessage(String message, String userAccessToken) {
		String postId = null;

		message = message.replaceAll(" ", "+");

		Response resp = given().auth().preemptive().oauth2(userAccessToken).body("message=" + message).when()
				.contentType(ContentType.JSON).post("https://graph.facebook.com/v3.2/me/feed");

		postId = resp.jsonPath().getString("id");

		LOGGER.info("postId = " + postId);

		return postId;
	}

	public static void updatePostText(String postId, String message, String userAccessToken) {
		message = message.replaceAll(" ", "+");

		Response resp = given().auth().preemptive().oauth2(userAccessToken).body("message=" + message).when()
				.contentType(ContentType.JSON).post("https://graph.facebook.com/3.2/" + postId);

		boolean success = Boolean.parseBoolean(resp.jsonPath().getString("success"));

		if (!success) {
			final String errorMessage = "The post could not be updated";
			RuntimeException e = new RuntimeException(errorMessage);
			LOGGER.error(errorMessage, e);
			throw e;
		}

		LOGGER.info("Post updated successfully!");

	}

}
