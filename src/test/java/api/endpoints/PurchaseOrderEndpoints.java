package api.endpoints;
import static io.restassured.RestAssured.*;
import api.payload.PurchaseOrder;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PurchaseOrderEndpoints {

	public static Response createPurchaseOrder(PurchaseOrder purchaseOrder) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.body(purchaseOrder).log().body()
				.when()
				.post(Routes.post_purchaseOrder_url)
				;
		return response;
	}

public static Response updatePurchaseOrder(String poId,PurchaseOrder purchaseOrder) {
	String authToken = AuthTokenUtility.getAuthToken();
	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.headers("Authorization","Bearer "+authToken)
			.pathParam("id",poId)
			.body(purchaseOrder)
			.when()
			.put(Routes.put_purchaseOrder_url)
			;
	return response;
}

public static Response deletePurchaseOrder(String poId) {
	String authToken = AuthTokenUtility.getAuthToken();
	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.headers("Authorization","Bearer "+authToken)
			.pathParam("id",poId)
			.when()
			.delete(Routes.delete_purchaseOrder_url)
			;
	return response;
}

public static Response getPurchaseOrderByid(String poId) {
	String authToken = AuthTokenUtility.getAuthToken();
	Response response = 
			given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.headers("Authorization","Bearer "+authToken)
			.pathParam("id", poId)
			.when()
			.get(Routes.get_purchaseOrder_by_id_url)
			;
	return response;
}











}
