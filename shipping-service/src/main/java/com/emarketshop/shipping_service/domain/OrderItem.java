package com.emarketshop.shipping_service.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import com.emarketshop.shipping_service.domain.id.OrderItemId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@IdClass(OrderItemId.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public final class OrderItem extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "product_id", nullable = false, updatable = false)
	private Integer productId;
	
	@Id
	@Column(name = "order_id", nullable = false, updatable = false)
	private Integer orderId;
	
	@Column(name = "ordered_quantity")
	private Integer orderedQuantity;
	
}










