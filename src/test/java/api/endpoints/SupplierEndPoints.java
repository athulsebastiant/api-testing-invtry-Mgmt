package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Client;
import api.payload.Supplier;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SupplierEndPoints {
	
	public static Response createSupplier(Supplier supplierPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.body(supplierPayload)
				.when()
				.post(Routes.post_supplier_url);
		return response;
	}
	
	public static Response getSupplierByid(String supplierId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", supplierId)
				.when()
				.get(Routes.get_supplier_by_id)
				;
		return response;
	}
	
	public static Response updateSupplier(String supplierId,Supplier supplierPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id",supplierId)
				.body(supplierPayload)
				.when()
				.put(Routes.put_supplier_url)
				;
		return response;
	}
	
	public static Response deleteSupplier(String supplierId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", supplierId)
				.when()
				.delete(Routes.delete_supplier_by_id)
				;
		return response;
	}
	
	public static Response getAllSuppliers() {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.when()
				.get(Routes.get_suppliers_url)
				;
		return response;
	}
	
	
}
