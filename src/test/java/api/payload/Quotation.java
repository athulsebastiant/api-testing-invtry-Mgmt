package api.payload;

import java.util.List;

public class Quotation {
	String id;
	String clientId;
	String status = "pending";
	
	private List<Product> products;
	
	public static class Product{
		private String productSupplierId;
		private int quantity;
		private Double unitPrice;
		public String getProductSupplierId() {
			return productSupplierId;
		}
		public void setProductSupplierId(String productSupplierId) {
			this.productSupplierId = productSupplierId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public Double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}
		@Override
		public String toString() {
			return "Product [productSupplierId=" + productSupplierId + ", quantity=" + quantity + ", unitPrice="
					+ unitPrice + "]";
		}
		
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Quotation [id=" + id + ", clientId=" + clientId + ", status=" + status + ", products=" + products + "]";
	}

	
	
}
