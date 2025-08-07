package api.endpoints;
import static io.restassured.RestAssured.*;
import api.payload.Quotation;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class QuotationEndpoints {

	public static Response createQuotation(Quotation qPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.body(qPayload)
				.when()
				.post(Routes.post_quotation_url)
				;
		return response;
	}
	
	public static Response getQuotationById(String qId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", qId)
				.when()
				.get(Routes.get_quotation_by_id_url)
				;
		return response;
	}
	
	public static Response ApproveQuotationById(String qId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", qId)
				.when()
				.put(Routes.approve_quotation_by_id_url)
				;
		return response;
		
	}
	
	public static Response FulfillQuotationById(String qId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", qId)
				.when()
				.put(Routes.fulfill_quotation_by_id_url)
				;
		return response;
		
	}
	
	public static Response RejectQuotationById(String qId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", qId)
				.when()
				.put(Routes.reject_quotation_by_id_url)
				;
		return response;
		
	}
	
	public static Response DeleteQuotationById(String qId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", qId)
				.when()
				.delete(Routes.delete_quotation_url)
				;
		return response;
		
	}
	
	
	
	
}
