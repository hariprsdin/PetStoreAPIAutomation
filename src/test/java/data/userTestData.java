package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import api.payload.User;

public class userTestData {
	public Faker faker = new Faker();;
	public User userPayload;
	public Logger log;

	@BeforeClass
	public void setupData() {
//	    faker = new Faker();
	    userPayload = new User();
	    userPayload.setId(faker.idNumber().hashCode());
	    userPayload.setUsername(faker.name().username());
	    userPayload.setFirstName(faker.name().firstName());
	    userPayload.setLastName(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	    userPayload.setPassword(faker.internet().password(5, 10));
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
	    log= LogManager.getLogger(this.getClass());
	    log.info("Setup data initiated......");
	}
}
