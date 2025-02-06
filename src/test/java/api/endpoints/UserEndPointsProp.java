package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.response.Response;
import api.payload.User;

public class UserEndPointsProp {
	
	static ResourceBundle getURL(){
	ResourceBundle routes = ResourceBundle.getBundle("routes");
	return routes;
	}
	
	public static Response createUser(User payload) {
		String post_url= getURL().getString("post_url");
		System.out.println(payload+"    >>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Post URL: " + post_url);
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		return response;
	}
	
	public static Response readUser(String userName) {
		String get_url= getURL().getString("get_url");
		Response response = given()
		.pathParam("username", userName)
		.when()
		.get(get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User payload) {
		String update_url= getURL().getString("update_url");
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		.when()
		.put(update_url);
		return response;
	}

	public static Response deleteUser(String userName) {
		String delete_url= getURL().getString("delete_url");
		Response response = given()
		.pathParam("username", userName)
		.when()
		.delete(delete_url);
		return response;
	}
}
