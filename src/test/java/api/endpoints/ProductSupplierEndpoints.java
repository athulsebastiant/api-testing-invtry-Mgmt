package api.endpoints;
import static io.restassured.RestAssured.*;
import api.payload.ProductSupplier;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductSupplierEndpoints {

	public static Response createProductSupplier(ProductSupplier psPayload) {
		
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.body(psPayload)
				.when()
				.post(Routes.post_productSupplier_url)
				;
		return response;
				
	}
	
	public static Response updateProductSupplier(String psId,ProductSupplier psPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id",psId)
				.body(psPayload)
				.when()
				.put(Routes.put_productSuppliers_url)
				;
		return response;
	}
	
	public static Response deleteProductSupplier(String psId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id",psId)
				.when()
				.delete(Routes.delete_productSuppliers_url)
				;
		return response;
	}
	
	
}
