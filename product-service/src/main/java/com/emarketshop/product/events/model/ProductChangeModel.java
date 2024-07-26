package com.emarketshop.product.events.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductChangeModel {
	private String type;
	private String action;
	private String productId;
	private String correlationId;

	public ProductChangeModel(String type, String action, String productId, String correlationId) {
		super();
		this.type = type;
		this.action = action;
		this.productId = productId;
		this.correlationId = correlationId;
	}
}
