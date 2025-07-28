package api.endpoints;

import io.github.cdimascio.dotenv.Dotenv;

public class Routes {
	private static final Dotenv dotenv = Dotenv.load();
	public static String baseUrl = dotenv.get("BASE_URL");
	
	// Users
	public static String signup_url = baseUrl+"/users/signup";
	public static String login_url = baseUrl+"/users/login";
	
	//Products
	public static String get_products_url = baseUrl + "/products/";
	public static String get_product_by_id = baseUrl + "/products/{id}";
	public static String post_products_url = baseUrl + "/products/";
	public static String patch_product_by_id = baseUrl + "/products/{id}";
	public static String delete_product_by_id = baseUrl + "/products/{id}";
	
}
