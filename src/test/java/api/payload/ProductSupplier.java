package api.payload;

public class ProductSupplier {
	String id;
	String productId;
	String supplierId;
	double unitPrice;
	int leadTimeDays;
	boolean preferred;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getLeadTimeDays() {
		return leadTimeDays;
	}
	public void setLeadTimeDays(int leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}
	public boolean isPreferred() {
		return preferred;
	}
	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}
	
}
