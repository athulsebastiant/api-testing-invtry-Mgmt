package api.ddtests;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.ProductEndpoints;
import api.payload.Product;

import api.utilities.DataProviders;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class DDProductsTests {
	String productId;
	File testImage1;
    File testImage2;
    File testImage3;
    File testImage4;
	
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
	
	@Test(priority = 1, dataProvider = "ProductData",dataProviderClass = DataProviders.class)
	public void testCreateProduct( String name, String sku, String description, String initialStock, String currentStock, String reorderLevel, String costPrice,String category,String reservedStock) {
		System.out.println(name+" " +sku+" " +description+" " +initialStock+" " +currentStock+" " +reorderLevel+" " +costPrice+" " +category+" " +reservedStock);
		Product productPayload = new Product();
		productPayload.setName(name);
		productPayload.setSku(sku);
		productPayload.setDescription(description);
		productPayload.setInitialStock(Integer.parseInt(initialStock));
		productPayload.setCurrentStock(Integer.parseInt(currentStock));
		productPayload.setReorderLevel(Integer.parseInt(reorderLevel));
		productPayload.setCostPrice(Double.parseDouble(costPrice));
		productPayload.setCategory(category);
		productPayload.setReservedStock(Integer.parseInt(reservedStock));
		setupTestImages();
		
		Response response = ProductEndpoints.createProduct(productPayload,testImage1,testImage2,testImage3,testImage4);
		
		response.then()
        .statusCode(201).body("message", equalTo("Product created successfully!")).body("success", equalTo(true));
		response.then().log().all();
		JSONObject jsonResponse = new JSONObject(response.asString());
	    JSONObject products = jsonResponse.getJSONObject("product");
		productId = products.getString("_id");
		SharedTestData.addProductId(name, productId);
	    
	    
	    Assert.assertEquals(products.getString("name"), productPayload.getName() );
	    Assert.assertEquals(products.getString("sku"),productPayload.getSku());
	    
	    Assert.assertEquals(products.getString("description"),productPayload.getDescription());
	    
	    Assert.assertEquals(products.get("initialStock"),productPayload.getInitialStock());
	    
	    Assert.assertEquals(products.get("currentStock"),productPayload.getCurrentStock());
	    
	    Assert.assertEquals(products.get("reorderLevel"),productPayload.getReorderLevel());
	    
	    Assert.assertEquals(Double.valueOf(products.get("costPrice").toString()),productPayload.getCostPrice());
	    
	    Assert.assertEquals(products.getString("category"),productPayload.getCategory());
	    
	    Assert.assertEquals(products.get("reservedStock"),productPayload.getReservedStock());
	    
	}
	
	
	@Test(priority = 2,dataProvider = "ProductData",dataProviderClass = DataProviders.class)
	public void testGetProduct( String name, String sku, String description, String initialStock, String currentStock, String reorderLevel, String costPrice,String category,String reservedStock) {
		Response response = 
				ProductEndpoints.getProductById(SharedTestData.getProductId(name));
		response.then().log().all();
		response.then().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productJsonSchema.json"));
		
JSONObject jsonResponse = new JSONObject(response.asString());
		
		Assert.assertEquals(jsonResponse.getString("_id"),SharedTestData.getProductId(name) );
		Assert.assertEquals(jsonResponse.getString("name"), name );
	    Assert.assertEquals(jsonResponse.getString("sku"),sku);
	    Assert.assertEquals(jsonResponse.getString("description"),description);
	    Assert.assertEquals(jsonResponse.get("initialStock"),Integer.parseInt(initialStock) );	    
	    Assert.assertEquals(jsonResponse.get("currentStock"),Integer.parseInt(currentStock));	    
	    Assert.assertEquals(jsonResponse.get("reorderLevel"),Integer.parseInt(reorderLevel));	    
	    Assert.assertEquals(Double.valueOf(jsonResponse.get("costPrice").toString()),Double.parseDouble(costPrice) );	    
	    Assert.assertEquals(jsonResponse.getString("category"),category);	    
	    Assert.assertEquals(jsonResponse.get("reservedStock"),Integer.parseInt(reservedStock));
	}
	
	
	@Test(priority = 3,dataProvider = "UpdatedProductData",dataProviderClass = DataProviders.class)

	public void testUpdateProduct(String name, String sku, String description,String reorderLevel, String costPrice,String category) {
		Product productPayload = new Product();
		productPayload.setName(name);
		productPayload.setSku(sku);
		productPayload.setDescription(description);
		productPayload.setReorderLevel(Integer.parseInt(reorderLevel));
		productPayload.setCostPrice(Double.parseDouble(costPrice));
		productPayload.setCategory(category);
		Response response = 
				ProductEndpoints.updateProduct(SharedTestData.getProductId(name), productPayload);
		response.then().log().all();
		response.then().statusCode(200).body("message", equalTo("Product updated successfully")).body("success", equalTo(true));
		JSONObject jsonResponse = new JSONObject(response.asString());
	    JSONObject products = jsonResponse.getJSONObject("updatedProduct");
	    Assert.assertEquals(products.getString("name"), productPayload.getName() );
	    Assert.assertEquals(products.getString("sku"),productPayload.getSku());
	    Assert.assertEquals(products.getString("description"),productPayload.getDescription());
 Assert.assertEquals(products.get("reorderLevel"),productPayload.getReorderLevel());
	    Assert.assertEquals(Double.valueOf(products.get("costPrice").toString()),productPayload.getCostPrice());
	    Assert.assertEquals(products.getString("category"),productPayload.getCategory());
	}
	
	
	
	@Test(priority = 4,dataProvider = "GetProductNames",dataProviderClass = DataProviders.class)
	public void testDeleteProduct(String name) {
		Response response = 
				ProductEndpoints.deleteProduct(SharedTestData.getProductId(name));
		response.then().log().all();
		response.then().statusCode(200);
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		Assert.assertEquals(message, "Product deleted succesfully");
	}
	
	
	
}
