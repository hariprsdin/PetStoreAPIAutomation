package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPointsRoutes;
import api.payload.User;
import data.userTestData;
import io.restassured.response.Response;

public class UserTests extends userTestData {
//	User userPayload;

//	@BeforeClass
//	public void setupData() {
//	    faker = new Faker();
//	    userPayload = new User();
//	    userPayload.setId(faker.idNumber().hashCode());
//	    userPayload.setUsername(faker.name().username());
//	    userPayload.setFirstName(faker.name().firstName());
//	    userPayload.setLastName(faker.name().lastName());
//	    userPayload.setEmail(faker.internet().safeEmailAddress());
//	    userPayload.setPassword(faker.internet().password(5, 10));
//	    userPayload.setPhone(faker.phoneNumber().cellPhone());
//	}

	@Test(priority = 1)
	public void testPostUser() {
		log.info("Creating a new user using post request");
		Response response = UserEndPointsRoutes.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("User created successfully using post request");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		log.info("Get the user name");
		Response response = UserEndPointsRoutes.readUser(userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Got the username and verified");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		log.info("Initial First Name: " + userPayload.getFirstName());
		log.info("Initial Last Name: " + userPayload.getLastName());
		log.info("Initial Email : " + userPayload.getEmail());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPointsRoutes.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().all();
		log.info("Updated First Name: " + userPayload.getFirstName());
		log.info("Updated Last Name: " + userPayload.getLastName());
		log.info("Updated Email : " + userPayload.getEmail());
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Update the user and got the status code 200");

		// Checking data after update
		Response responseAfter = UserEndPointsRoutes.readUser(userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(responseAfter.getStatusCode(), 200);
		log.info("Verify the user data after updating the record");
	}

	@Test(priority = 4)
	public void deleteUserByName() {
		log.info("Going to delete the user: " + userPayload.getUsername());
		Response response = UserEndPointsRoutes.deleteUser(userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("User " + userPayload.getUsername() + " deleted successfully");
	}
}
