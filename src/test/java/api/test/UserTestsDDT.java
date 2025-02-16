package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndPointsProp;
import api.payload.User;
import api.utilities.DataProviders;
import data.userTestData;
import io.restassured.response.Response;

public class UserTestsDDT extends userTestData {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String useremail, String pwd,
			String ph) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		Response response = UserEndPointsProp.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("User " + userName + " created successfully");
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPointsProp.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("User " + userName + " deleted successfully");
	}
}
