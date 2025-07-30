package api.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrder {
	String id;
	@JsonProperty("supplierId")
	String supplierId;
	public enum Status {pending,delivered,cancelled};
	Status status = Status.pending;
	private List<Item> items;
	
	public static class Item{
		private String productSupplierId;
		private int quantityOrdered;
		private Double unitPrice;
		public String getProductSupplierId() {
			return productSupplierId;
		}
		public void setProductSupplierId(String productSupplierId) {
			this.productSupplierId = productSupplierId;
		}
		public int getQuantityOrdered() {
			return quantityOrdered;
		}
		public void setQuantityOrdered(int quantityOrdered) {
			this.quantityOrdered = quantityOrdered;
		}
		public Double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}
		@Override
		public String toString() {
			return "Item [productSupplierId=" + productSupplierId + ", quantityOrdered=" + quantityOrdered
					+ ", unitPrice=" + unitPrice + "]";
		}
		
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", supplierId=" + supplierId + ", status=" + status + ", items=" + items
				+ "]";
	}

	
}
