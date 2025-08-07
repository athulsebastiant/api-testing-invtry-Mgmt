package api.test;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.QuotationEndpoints;

import api.payload.Quotation;

import io.restassured.response.Response;

public class QuotationTests {
	String qId;
	Quotation qPayload;
	ProductSupplierTest productSupplierTest = new ProductSupplierTest();
	String productSupplierId;
	String clientId;
	ClientTests clientTests = new ClientTests();
	
	@BeforeClass
	public void setUp() {
		qPayload = new Quotation();
		productSupplierTest.setUp();
		productSupplierTest.testCreateProductSupplier();
		clientTests.setUp();
		clientTests.testCreateClient();
		productSupplierId = TestDataStore.productSupplierIds.get(0);
		clientId = TestDataStore.clientIds.get(0);
		
		qPayload.setClientId(clientId);
		Quotation.Product product = new Quotation.Product();
		product.setProductSupplierId(productSupplierId);
		product.setQuantity(5);
		product.setUnitPrice(95.0);
		qPayload.setProducts(List.of(product));
		System.out.println(qPayload.toString());
		
	}
	
	@Test(priority =1)
	public void testCreateQuotation() {
		
		Response response = QuotationEndpoints.createQuotation(qPayload);
		response.then().log().all();
		response.then()
        .statusCode(201).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject quotation = jsonResponse.getJSONObject("quotation");
		JSONArray productsArray = quotation.getJSONArray("products");
		JSONObject firstItem = productsArray.getJSONObject(0);
		Quotation.Product expectedProduct = qPayload.getProducts().get(0);
		qId = quotation.getString("_id");
		this.qPayload.setId(qId);
		String status = quotation.getString("status");
		this.qPayload.setStatus(status);
		Assert.assertEquals(quotation.getString("clientId"), this.qPayload.getClientId());
		Assert.assertEquals(firstItem.getString("productSupplierId"), expectedProduct.getProductSupplierId());
		Assert.assertEquals(firstItem.getInt("quantity"), expectedProduct.getQuantity());
		Assert.assertEquals(firstItem.getDouble("unitPrice"), expectedProduct.getUnitPrice());
		
	}
	
	@Test(priority = 2)
	public void testGetQuotationById() {
		Response response = QuotationEndpoints.getQuotationById(this.qPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
	}
	
	
	//@Test(priority = 3)
	public void testApproveQuotationById() {
		Response response = QuotationEndpoints.ApproveQuotationById(this.qPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
	}
	
	@Test(priority = 4)
	public void testFulfillQuotationById() {
		Response response = QuotationEndpoints.FulfillQuotationById(this.qPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
	}
	
	
	
	@Test(priority = 5)
	public void testDeleteQuotation() {
		Response response = QuotationEndpoints.DeleteQuotationById(this.qPayload.getId());
		response.then().log().all();
	}
	
	@AfterClass
	public void tearDown() {
		productSupplierTest.testDeleteProductSupplier();
		productSupplierTest.tearDown();
		clientTests.testDeleteClient();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
