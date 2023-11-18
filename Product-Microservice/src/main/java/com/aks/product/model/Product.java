package com.aks.product.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String producId;
	private String productName;
	private String productDesc;
	private Integer qtyInStock;
	private String prodCategory;
	private String price;
	private String prodManufacturer;
	private String prodMfgDate;
	private String vendor;
	private String version;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;

	public String getProducId() {
		return producId;
	}

	public void setProducId(String producId) {
		this.producId = producId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(Integer qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProdManufacturer() {
		return prodManufacturer;
	}

	public void setProdManufacturer(String prodManufacturer) {
		this.prodManufacturer = prodManufacturer;
	}

	public String getProdMfgDate() {
		return prodMfgDate;
	}

	public void setProdMfgDate(String prodMfgDate) {
		this.prodMfgDate = prodMfgDate;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
