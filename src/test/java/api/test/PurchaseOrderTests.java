package api.test;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PurchaseOrderEndpoints;
import api.payload.PurchaseOrder;
import api.payload.PurchaseOrder.Status;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PurchaseOrderTests {
	public static String poId;
	PurchaseOrder poPayload;
	ProductSupplierTest productSupplierTest = new ProductSupplierTest();
	String supplierId;
	String productSupplierId;
	@BeforeClass
	public void setUp() {
		poPayload = new PurchaseOrder();
		productSupplierTest.setUp();
		productSupplierTest.testCreateProductSupplier();
		supplierId = TestDataStore.supplierIds.get(0);
		productSupplierId = TestDataStore.supplierToProductSupplierIds.get(supplierId).get(0);

		poPayload.setSupplierId(supplierId);
		poPayload.setStatus(Status.pending);
		PurchaseOrder.Item item =  new PurchaseOrder.Item();
		item.setProductSupplierId(productSupplierId);
		item.setQuantityOrdered(5);
		item.setUnitPrice(95.0);
		poPayload.setItems(List.of(item));
		System.out.println(poPayload.toString());
	}
	
	@Test(priority = 1)
	public void testCreatePurchaseorder() {
		
		Response response = PurchaseOrderEndpoints.createPurchaseOrder(poPayload);
		response.then().log().all();
		response.then()
        .statusCode(201);
		JSONObject jsonResponse = new JSONObject(response.asString());
		poId = jsonResponse.getString("_id");
		this.poPayload.setId(poId);
		Assert.assertEquals(jsonResponse.getString("supplierId"), poPayload.getSupplierId());
		Assert.assertEquals(jsonResponse.getString("status"), poPayload.getStatus().toString());
		JSONArray itemsArray = jsonResponse.getJSONArray("items");
		JSONObject firstItem = itemsArray.getJSONObject(0);
		PurchaseOrder.Item expectedItem = poPayload.getItems().get(0);
		Assert.assertEquals(firstItem.getString("productSupplierId"), expectedItem.getProductSupplierId());
		Assert.assertEquals(firstItem.getInt("quantityOrdered"), expectedItem.getQuantityOrdered());
		Assert.assertEquals(firstItem.getDouble("unitPrice"), expectedItem.getUnitPrice());
		
		
	}
	
	
	@Test(priority = 2)
	public void testGetPurchaseOrderById() {
		
		Response response = PurchaseOrderEndpoints.getPurchaseOrderByid(this.poPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("purchaseOrderJsonSchema.json"));
		
	}
	
	@Test(priority = 3)
	public void testUpdatePurchaseOrder() {
		poPayload.setStatus(Status.delivered);
		Response response = PurchaseOrderEndpoints.updatePurchaseOrder(this.poPayload.getId(),poPayload);
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		Assert.assertEquals(jsonResponse.getString("supplierId"), poPayload.getSupplierId());
		Assert.assertEquals(jsonResponse.getString("status"), poPayload.getStatus().toString());
		JSONArray itemsArray = jsonResponse.getJSONArray("items");
		JSONObject firstItem = itemsArray.getJSONObject(0);
		PurchaseOrder.Item expectedItem = poPayload.getItems().get(0);
		Assert.assertEquals(firstItem.getString("productSupplierId"), expectedItem.getProductSupplierId());
		Assert.assertEquals(firstItem.getInt("quantityOrdered"), expectedItem.getQuantityOrdered());
		Assert.assertEquals(firstItem.getDouble("unitPrice"), expectedItem.getUnitPrice());
	}
	
	@Test(priority = 4)
	public void testDeletePurchaseOrder() {
		Response response = PurchaseOrderEndpoints.deletePurchaseOrder(this.poPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "PO deleted");
	}
	
	@AfterClass
	public void tearDown() {
		productSupplierTest.testDeleteProductSupplier();
		productSupplierTest.tearDown();
	}
	
	
	
	
	
	
	
	
	
	
	
}
