package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.ProductEndpoints;
import api.payload.Product;
import io.restassured.response.Response;

public class ProductTests {
	String productId;
	Product productPayload;
	File testImage1;
    File testImage2;
    File testImage3;
    File testImage4;
	@BeforeClass
	public void setUp() {
		productPayload = new Product();
		productPayload.setName("Product1");
		productPayload.setSku("SKUTEST1");
		productPayload.setDescription("Test Description for Product");
		productPayload.setInitialStock(20);
		productPayload.setCurrentStock(20);
		productPayload.setReorderLevel(5);
		productPayload.setCostPrice(100.0);
		productPayload.setCategory("TestCategory");
		productPayload.setReservedStock(0);
		setupTestImages();
		
	}
	
	private void setupTestImages() {
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
    		testImage1 = new File(classLoader.getResource("testImages/image1.webp").getFile());
    		testImage2 = new File(classLoader.getResource("testImages/image2.avif").getFile());
    		testImage3 = new File(classLoader.getResource("testImages/image3.jpg").getFile());
    		testImage4 = new File(classLoader.getResource("testImages/image4.webp").getFile());
        }catch (Exception e) {
            System.err.println("Error setting up test images: " + e.getMessage());
        }
	}
	
	
	@Test(priority = 1)
	public void testCreateProduct() {
		Response response = ProductEndpoints.createProduct(productPayload,testImage1,testImage2,testImage3,testImage4 );
		response.then()
        .statusCode(201).body("message", equalTo("Product created successfully!")).body("success", equalTo(true));
		response.then().log().all();
		JSONObject jsonResponse = new JSONObject(response.asString());
	    JSONObject products = jsonResponse.getJSONObject("product");
		productId = products.getString("_id");
		TestDataStore.productIds.add(productId);
	    this.productPayload.setId(productId);
	    
	    Assert.assertEquals(products.getString("name"), this.productPayload.getName() );
	    Assert.assertEquals(products.getString("sku"),this.productPayload.getSku());
	    
	    Assert.assertEquals(products.getString("description"),this.productPayload.getDescription());
	    
	    Assert.assertEquals(products.get("initialStock"),this.productPayload.getInitialStock());
	    
	    Assert.assertEquals(products.get("currentStock"),this.productPayload.getCurrentStock());
	    
	    Assert.assertEquals(products.get("reorderLevel"),this.productPayload.getReorderLevel());
	    
	    Assert.assertEquals(Double.valueOf(products.get("costPrice").toString()),this.productPayload.getCostPrice());
	    
	    Assert.assertEquals(products.getString("category"),this.productPayload.getCategory());
	    
	    Assert.assertEquals(products.get("reservedStock"),this.productPayload.getReservedStock());
	    JSONArray imagesArray = products.getJSONArray("imagesUrl");
		String[] imagesUrl = new String[imagesArray.length()];

		for (int i = 0; i < imagesArray.length(); i++) {
		    imagesUrl[i] = imagesArray.getString(i);
		}

		this.productPayload.setImagesUrl(imagesUrl);
	}
	
	@Test(priority = 2,dependsOnMethods = {"testCreateProduct"})
	public void testGetProductById() {
		Response response = 
				ProductEndpoints.getProductById(this.productPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productJsonSchema.json"));
		JSONObject jsonResponse = new JSONObject(response.asString());
		
		Assert.assertEquals(jsonResponse.getString("_id"), this.productPayload.getId());
		Assert.assertEquals(jsonResponse.getString("name"), this.productPayload.getName() );
	    Assert.assertEquals(jsonResponse.getString("sku"),this.productPayload.getSku());
	    
	    Assert.assertEquals(jsonResponse.getString("description"),this.productPayload.getDescription());
	    
	    Assert.assertEquals(jsonResponse.get("initialStock"),this.productPayload.getInitialStock());
	    
	    Assert.assertEquals(jsonResponse.get("currentStock"),this.productPayload.getCurrentStock());
	    
	    Assert.assertEquals(jsonResponse.get("reorderLevel"),this.productPayload.getReorderLevel());
	    
	    Assert.assertEquals(Double.valueOf(jsonResponse.get("costPrice").toString()),this.productPayload.getCostPrice());
	    
	    Assert.assertEquals(jsonResponse.getString("category"),this.productPayload.getCategory());
	    
	    Assert.assertEquals(jsonResponse.get("reservedStock"),this.productPayload.getReservedStock());
	}
	
	@Test(priority = 3,dependsOnMethods = {"testCreateProduct"})
	public void testUpdateProduct() {
		productPayload.setName("Product10");
		productPayload.setSku("SKUTEST10");
		productPayload.setDescription("Test Description for Product1");
		productPayload.setReorderLevel(50);
		productPayload.setCostPrice(1000);
		productPayload.setCategory("TestCategory1");
		
		Response response = 
				ProductEndpoints.updateProduct(this.productPayload.getId(), productPayload);
		response.then().log().all();
		response.then().statusCode(200).body("message", equalTo("Product updated successfully")).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
	    JSONObject products = jsonResponse.getJSONObject("updatedProduct");
	    Assert.assertEquals(products.getString("name"), this.productPayload.getName() );
	    Assert.assertEquals(products.getString("sku"),this.productPayload.getSku());
	    
	    Assert.assertEquals(products.getString("description"),this.productPayload.getDescription());
	    
	    Assert.assertEquals(products.get("initialStock"),this.productPayload.getInitialStock());
	    
	    Assert.assertEquals(products.get("currentStock"),this.productPayload.getCurrentStock());
	    
	    Assert.assertEquals(products.get("reorderLevel"),this.productPayload.getReorderLevel());
	    
	    Assert.assertEquals(Double.valueOf(products.get("costPrice").toString()),this.productPayload.getCostPrice());
	    
	    Assert.assertEquals(products.getString("category"),this.productPayload.getCategory());
	    
	    Assert.assertEquals(products.get("reservedStock"),this.productPayload.getReservedStock());
	}
	
	@Test(priority = 4,dependsOnMethods = {"testCreateProduct"})
	public void testDeleteProduct() {
		Response response = 
				ProductEndpoints.deleteProduct(this.productPayload.getId());
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "Product deleted succesfully");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
