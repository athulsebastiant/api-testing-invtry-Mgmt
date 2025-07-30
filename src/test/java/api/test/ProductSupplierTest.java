package api.test;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.ProductSupplierEndpoints;
import api.payload.ProductSupplier;
import io.restassured.response.Response;

public class ProductSupplierTest {
	ProductSupplier psPayload;
	String psId;
	SupplierTests supplierTest = new SupplierTests();
	String supplierId;
	ProductTests productTest = new ProductTests();
	String productId;
	
	@BeforeClass
	public void setUp() {
		psPayload = new ProductSupplier();
		
		supplierTest.setup();
		supplierTest.testCreateSupplier();
		supplierId = TestDataStore.supplierIds.get(0);
		productTest.setUp();
		productTest.testCreateProduct();
		productId = TestDataStore.productIds.get(0);
		psPayload.setProductId(productId);
		psPayload.setSupplierId(supplierId);
		psPayload.setUnitPrice(95.0);
		psPayload.setPreferred(false);
		psPayload.setLeadTimeDays(2);
		
	}
	
	@Test(priority = 1)
	public void testCreateProductSupplier() {
		Response response = ProductSupplierEndpoints.createProductSupplier(psPayload);
		response.then().log().all();
		response.then()
        .statusCode(201).body("message", equalTo("Product Supplier Link created")).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject newLink = jsonResponse.getJSONObject("newLink");
		psId = newLink.getString("_id");
		
		TestDataStore.productSupplierIds.add(psId);
		TestDataStore.supplierToProductSupplierIds.computeIfAbsent(supplierId,k->new ArrayList<>()).add(psId);
		/*If supplierId is already in the map, it returns the existing List<String>

If not, it creates a new ArrayList<String>, puts it into the map under supplierId, and then returns it.
After we get the list (either existing or newly created), we add psId (a productSupplierId) to that list.
*/
		this.psPayload.setId(psId);
		
		Assert.assertEquals(newLink.getString("productId"), this.psPayload.getProductId());
		
		Assert.assertEquals(newLink.getString("supplierId"), this.psPayload.getSupplierId());
		
//		TestDataStore.supplierId = this.psPayload.getSupplierId();
		Assert.assertEquals(Double.valueOf(newLink.get("unitPrice").toString()), this.psPayload.getUnitPrice());

		Assert.assertEquals(newLink.get("leadTimeDays"), this.psPayload.getLeadTimeDays());
		Assert.assertEquals(newLink.get("preferred"), this.psPayload.isPreferred());
	}
	

	
	@Test(priority = 2)
	public void testUpdateProductSupplier() {
		psPayload.setUnitPrice(96.0);
		psPayload.setPreferred(true);
		psPayload.setLeadTimeDays(3);
		
		
		
		Response response = ProductSupplierEndpoints.updateProductSupplier(this.psPayload.getId(), psPayload);
		response.then().log().all();
		response.then().statusCode(200).body("message", equalTo("Links updated successfully")).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
		JSONObject updatedLink = jsonResponse.getJSONObject("link");
		Assert.assertEquals(Double.valueOf(updatedLink.get("unitPrice").toString()), this.psPayload.getUnitPrice());

		Assert.assertEquals(updatedLink.get("leadTimeDays"), this.psPayload.getLeadTimeDays());
		Assert.assertEquals(updatedLink.get("preferred"), this.psPayload.isPreferred());
	}
	
	@Test(priority = 3)
	public void testDeleteProductSupplier() {
		Response response = ProductSupplierEndpoints.deleteProductSupplier(this.psPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "Link deleted successfully");
	}
	
	
	
	
	
	
	
	
	
	@AfterClass
	public void tearDown() {
		supplierTest.testDeleteSupplier();
		productTest.testDeleteProduct();
	}
	
	
}
