package api.ddtests;

import java.util.HashMap;
import java.util.Map;

public class SharedTestData {
	private static final Map<String, String> productIds = new HashMap<>();

    public static void addProductId(String productName, String productId) {
        productIds.put(productName, productId);
    }

    public static String getProductId(String productName) {
        return productIds.get(productName);
    }

    public static Map<String, String> getAllProductIds() {
        return productIds;
    }

    public static void clearProductIds() {
        productIds.clear();
    }

}
