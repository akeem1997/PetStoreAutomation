package api.endpoints;

//userEndpoints,java
//created for perform create,read,update, delete requests in the user API
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import api.payload.User;

public class userEndPoints {
	
	public static Response createUser(User payload ) {
		Response res=given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload).when().post(Routes.post_url);
		return res;
		
		
	}
	public static Response readUser(String userName) {
		Response res=given().pathParam("username", userName)
		.when().get(Routes.get_url);
		return res;
		
	}
	public static Response updateUser(User payload, String userName ) {
		Response res=given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload).when().put(Routes.update_url);
		return res;

}
	public static Response deleteUser(String userName) {
		Response res=given().pathParam("username", userName)
		.when().delete(Routes.delete_url);
		return res;
		
	}
	
}
