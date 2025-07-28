package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;

import api.payload.Product;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static api.test.UserTests.token;
public class ProductEndpoints {

	public static Response createProduct(Product productPayload, File... imagesFiles) {
		
		String authToken = AuthTokenUtility.getAuthToken();
		
		
		RequestSpecification request = given().contentType(ContentType.MULTIPART).header("Authorization", "Bearer " + authToken);

		if (productPayload.getName() != null) {
			request.multiPart("name", productPayload.getName());
		}

		if(productPayload.getSku() != null) {
			request.multiPart("sku",productPayload.getSku());
		}
		
		if (productPayload.getDescription() != null) {
			request.multiPart("description", productPayload.getDescription());

		}

		request.multiPart("initialStock", productPayload.getInitialStock());
		request.multiPart("currentStock",productPayload.getCurrentStock());
		request.multiPart("reorderLevel",productPayload.getReorderLevel());
		request.multiPart("costPrice",productPayload.getCostPrice());
		if(productPayload.getCategory() != null) {
			request.multiPart("category",productPayload.getCategory());
		}
		
		request.multiPart("reservedStock",productPayload.getReservedStock());
	
		if(imagesFiles != null && imagesFiles.length > 0) {
			for(int i=0;i<imagesFiles.length && i <4;i++) {
				String fieldName = "image" + (i+1);
				request.multiPart(fieldName,imagesFiles[i],"image/*");
				
			}
		}
	
		Response response = request
	            .when()
	            .post(Routes.post_products_url);
	    
	    return response;
	}

	public static Response getProductById(String productId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).headers("Authorization","Bearer "+authToken).pathParam("id", productId)
				.when().get(Routes.get_product_by_id);
		return response;
	}

	public static Response updateProduct(String productId, Product productPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).headers("Authorization","Bearer "+authToken).pathParam("id", productId)
				.body(productPayload).when().patch(Routes.patch_product_by_id);
		return response;
	}

	public static Response deleteProduct(String productId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).headers("Authorization","Bearer "+authToken).pathParam("id", productId)
				.when().delete(Routes.delete_product_by_id);
		return response;
	}
}
