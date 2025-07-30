package api.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataStore {
//	public static String supplierId;
//    public static String productId;
//    public static String productSupplierId;
	public static List<String> productIds = new ArrayList<>();
	public static List<String> supplierIds = new ArrayList<>();
	public static List<String> productSupplierIds = new ArrayList<>();
	
	public static Map<String,List<String>> supplierToProductSupplierIds = new HashMap<>();
	
	public static void clear() {
        productIds.clear();
        supplierIds.clear();
        productSupplierIds.clear();
        supplierToProductSupplierIds.clear();
    }
}
