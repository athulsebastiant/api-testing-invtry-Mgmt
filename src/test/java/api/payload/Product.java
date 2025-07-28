package api.payload;

public class Product {
	String id;
	String name;
	String sku;
	String description;
	int initialStock=0;
	int currentStock=0;
	int reorderLevel=10;
	double costPrice=0;
	String Category;
	String [] imagesUrl;
	int reservedStock=0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getInitialStock() {
		return initialStock;
	}
	public void setInitialStock(int initialStock) {
		this.initialStock = initialStock;
	}
	public int getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}
	public int getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String[] getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String[] imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public int getReservedStock() {
		return reservedStock;
	}
	public void setReservedStock(int reservedStock) {
		this.reservedStock = reservedStock;
	}
}
