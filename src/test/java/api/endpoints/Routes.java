package api.endpoints;

import io.github.cdimascio.dotenv.Dotenv;

public class Routes {
	private static final Dotenv dotenv = Dotenv.load();
	public static String baseUrl = dotenv.get("BASE_URL");
	
	// Users
	public static String signup_url = baseUrl+"/users/signup";
	public static String login_url = baseUrl+"/users/login";
	
	//Products
	public static String get_products_url = baseUrl + "/products/"; //yet to implement
	public static String get_product_by_id = baseUrl + "/products/{id}";
	public static String post_products_url = baseUrl + "/products/";
	public static String patch_product_by_id = baseUrl + "/products/{id}";
	public static String delete_product_by_id = baseUrl + "/products/{id}";
	
	//Clients
	public static String get_clients_url = baseUrl + "/clients/"; //yet to implement
	public static String post_client_url = baseUrl+"/clients/";
	public static String get_client_by_id_url = baseUrl+"/clients/{id}";
	public static String put_client_url = baseUrl+"/clients/{id}";
	public static String delete_client_url = baseUrl+"/clients/{id}";
	
	//Suppliers
	public static String get_suppliers_url = baseUrl+"/suppliers/";//yet to implement
	public static String post_supplier_url = baseUrl + "/suppliers/";
	public static String put_supplier_url = baseUrl + "/suppliers/{id}";
	public static String get_supplier_by_id = baseUrl + "/suppliers/{id}";
	public static String delete_supplier_by_id = baseUrl + "/suppliers/{id}";
	
}
