package api.test;

import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import api.endpoints.SupplierEndPoints;
import api.payload.Supplier;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class SupplierTests {
	Supplier supplierpayload;
	String supplierId;
	
	@BeforeClass
	public void setup() {
		supplierpayload = new Supplier();
		supplierpayload.setName("TestSupplier1");
		supplierpayload.setPhone("1234567891");
		supplierpayload.setEmail("testsupplier@gmail.com");
		supplierpayload.setAddress("Test supplier 1 address");
	}
	
	@Test(priority = 1)
	public void testCreateSupplier() {
		Response response = SupplierEndPoints.createSupplier(supplierpayload);
		response.then()
        .statusCode(201).body("message", equalTo("Supplier created")).body("success", equalTo(true));
		response.then().log().all();
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject supplierObject = jsonResponse.getJSONObject("supplier");
		supplierId = supplierObject.getString("_id");
		TestDataStore.supplierIds.add(supplierId);
		this.supplierpayload.setId(supplierId);
		Assert.assertEquals(supplierObject.getString("name"), this.supplierpayload.getName());
		Assert.assertEquals(supplierObject.getString("email"), this.supplierpayload.getEmail());
		Assert.assertEquals(supplierObject.getString("phone"), this.supplierpayload.getPhone());
		Assert.assertEquals(supplierObject.getString("address"), this.supplierpayload.getAddress());
	}
	
	@Test(priority = 2)
	public void testGetSupplierById() {
		Response response = SupplierEndPoints.getSupplierByid(this.supplierpayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("supplierJsonSchema.json"));
		JSONObject suppliertObject = new JSONObject(response.asString());
		
		Assert.assertEquals(suppliertObject.getString("name"), this.supplierpayload.getName());
		Assert.assertEquals(suppliertObject.getString("email"), this.supplierpayload.getEmail());
		Assert.assertEquals(suppliertObject.getString("phone"), this.supplierpayload.getPhone());
		Assert.assertEquals(suppliertObject.getString("address"), this.supplierpayload.getAddress());
		
	}
	
	@Test(priority = 3)
	public void testUpdateSupplier() {
		
		supplierpayload.setName("TestSupplier10");
		supplierpayload.setPhone("12345678910");
		supplierpayload.setEmail("testsupplier0@gmail.com");
		supplierpayload.setAddress("Test supplier 1 address000");
		Response response = SupplierEndPoints.updateSupplier(this.supplierpayload.getId(), supplierpayload);
		response.then().log().all();
		response.then().statusCode(200).body("message", equalTo("Supplier Data Updated successfully")).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject supplierObject = jsonResponse.getJSONObject("updated");
		Assert.assertEquals(supplierObject.getString("name"), this.supplierpayload.getName());
		Assert.assertEquals(supplierObject.getString("email"), this.supplierpayload.getEmail());
		Assert.assertEquals(supplierObject.getString("phone"), this.supplierpayload.getPhone());
		Assert.assertEquals(supplierObject.getString("address"), this.supplierpayload.getAddress());
	}
	@Test(priority = 4)
	public void testDeleteSupplier() {
		Response response = SupplierEndPoints.deleteSupplier(this.supplierpayload.getId());
		response.then().log().all();
		response.then().statusCode(200);	
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "Supplier deleted");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
