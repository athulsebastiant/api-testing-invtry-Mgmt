package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.ClientEndpoints;
import api.payload.Client;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
public class ClientTests {
	Client clientPayload;
	String id;
	
	@BeforeClass
	public void setUp() {
		String [] tags = {"mvp","top"};
		clientPayload = new Client();
		clientPayload.setName("TestClient1");
		clientPayload.setEmail("testclient01@gmail.com");
		clientPayload.setContactPerson("Test Contact Person");
		clientPayload.setAddress("Test Address 123");
		clientPayload.setCompanyType(Client.CompanyType.corporate);
		clientPayload.setGstNumber("GST1215213");
		clientPayload.setPhone("1234567890");
		clientPayload.setNotes("test notes 01");
		clientPayload.setPreferredContactMethod(Client.PreferredContactMethod.email);
		clientPayload.setStatus(Client.Status.active);
		clientPayload.setTags(tags);
		
	}
	
	@Test(priority = 1)
	public void testCreateClient() {
		Response response = ClientEndpoints.createClient(clientPayload);
		response.then()
        .statusCode(201).body("message", equalTo("Client created")).body("success", equalTo(true));
		response.then().log().all();
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject clientObject = jsonResponse.getJSONObject("client");
		id = clientObject.getString("_id");
		TestDataStore.clientIds.add(id);
		this.clientPayload.setId(id);
		Assert.assertEquals(clientObject.getString("name"), this.clientPayload.getName());
		Assert.assertEquals(clientObject.getString("contactPerson"), this.clientPayload.getContactPerson());
		Assert.assertEquals(clientObject.getString("phone"),this.clientPayload.getPhone());
		Assert.assertEquals(clientObject.getString("email"), this.clientPayload.getEmail());
		Assert.assertEquals(clientObject.getString("address"), this.clientPayload.getAddress());
		Assert.assertEquals(clientObject.getString("gstNumber"), this.clientPayload.getGstNumber());
		Assert.assertEquals(clientObject.getString("companyType"), this.clientPayload.getCompanyType().toString());
		Assert.assertEquals(clientObject.getString("notes"), this.clientPayload.getNotes());
		Assert.assertEquals(clientObject.getString("status"),this.clientPayload.getStatus().toString());
		Assert.assertEquals(clientObject.getString("preferredContactMethod"), this.clientPayload.getPreferredContactMethod().toString());
		JSONArray jsonArray = clientObject.getJSONArray("tags");
		String[] actualTags = new String[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
		    actualTags[i] = jsonArray.getString(i);
		}
		Assert.assertEquals(actualTags, this.clientPayload.getTags());
	}
	
	@Test(priority = 2)
	public void testGetClientById() {
		Response response = ClientEndpoints.getClientByid(this.clientPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("clientJsonSchema.json"));
		JSONObject clientObject = new JSONObject(response.asString());
		Assert.assertEquals(clientObject.getString("name"), this.clientPayload.getName());
		Assert.assertEquals(clientObject.getString("contactPerson"), this.clientPayload.getContactPerson());
		Assert.assertEquals(clientObject.getString("phone"),this.clientPayload.getPhone());
		Assert.assertEquals(clientObject.getString("email"), this.clientPayload.getEmail());
		Assert.assertEquals(clientObject.getString("address"), this.clientPayload.getAddress());
		Assert.assertEquals(clientObject.getString("gstNumber"), this.clientPayload.getGstNumber());
		Assert.assertEquals(clientObject.getString("companyType"), this.clientPayload.getCompanyType().toString());
		Assert.assertEquals(clientObject.getString("notes"), this.clientPayload.getNotes());
		Assert.assertEquals(clientObject.getString("status"),this.clientPayload.getStatus().toString());
		Assert.assertEquals(clientObject.getString("preferredContactMethod"), this.clientPayload.getPreferredContactMethod().toString());
		JSONArray jsonArray = clientObject.getJSONArray("tags");
		String[] actualTags = new String[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
		    actualTags[i] = jsonArray.getString(i);
		}
		Assert.assertEquals(actualTags, this.clientPayload.getTags());
	}
	
	@Test(priority = 3)
	public void testUpdateClient() {
		String [] tags = {"mvp","top","long-term"};
		clientPayload.setName("TestClient10");
		clientPayload.setEmail("testclient010@gmail.com");
		clientPayload.setContactPerson("Test Contact Person0");
		clientPayload.setAddress("Test Address 1230");
		clientPayload.setCompanyType(Client.CompanyType.individual);
		clientPayload.setGstNumber("GST12152130");
		clientPayload.setPhone("12345678900");
		clientPayload.setNotes("test notes 010");
		this.clientPayload.setPreferredContactMethod(Client.PreferredContactMethod.phone);
		clientPayload.setStatus(Client.Status.inactive);
		clientPayload.setTags(tags);
		
		Response response = ClientEndpoints.updateClient(this.clientPayload.getId(), clientPayload);
		response.then().log().all();
		response.then().statusCode(200).body("message", equalTo("Client Detail Updated")).body("success", equalTo(true));
	
		
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject clientObject = jsonResponse.getJSONObject("client");
		Assert.assertEquals(clientObject.getString("name"), this.clientPayload.getName());
		Assert.assertEquals(clientObject.getString("contactPerson"), this.clientPayload.getContactPerson());
		Assert.assertEquals(clientObject.getString("phone"),this.clientPayload.getPhone());
		Assert.assertEquals(clientObject.getString("email"), this.clientPayload.getEmail());
		Assert.assertEquals(clientObject.getString("address"), this.clientPayload.getAddress());
		Assert.assertEquals(clientObject.getString("gstNumber"), this.clientPayload.getGstNumber());
		Assert.assertEquals(clientObject.getString("companyType"), this.clientPayload.getCompanyType().toString());
		Assert.assertEquals(clientObject.getString("notes"), this.clientPayload.getNotes());
		Assert.assertEquals(clientObject.getString("status"),this.clientPayload.getStatus().toString());
		Assert.assertEquals(clientObject.getString("preferredContactMethod"), this.clientPayload.getPreferredContactMethod().toString());
		JSONArray jsonArray = clientObject.getJSONArray("tags");
		String[] actualTags = new String[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
		    actualTags[i] = jsonArray.getString(i);
		}
		Assert.assertEquals(actualTags, this.clientPayload.getTags());
	}
	
	@Test(priority = 4)
	public void testDeleteClient() {
		Response response = ClientEndpoints.deleteClient(this.clientPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "Client deleted successfully");
	}
}
