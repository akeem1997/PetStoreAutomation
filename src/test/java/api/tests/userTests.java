package api.tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTests {
	Faker faker;
	User userPayLoad;
	
	
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayLoad = new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password());
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		Response res=userEndPoints.createUser(userPayLoad);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		JSONObject jb = new JSONObject(res.asString());
		String getMess=jb.get("message").toString();
		System.out.println(getMess);
	}
	@Test(priority = 2)
	public void getUserByName() {
		Response res= userEndPoints.readUser(this.userPayLoad.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		//update data using payload
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		Response res=userEndPoints.updateUser(userPayLoad, this.userPayLoad.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		//checking data after update
		Response resAfterUpdate=userEndPoints.createUser(userPayLoad);
		res.then().log().all();
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
	}
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response res= userEndPoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
