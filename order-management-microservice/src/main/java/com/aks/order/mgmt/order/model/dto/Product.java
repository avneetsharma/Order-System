package com.aks.order.mgmt.order.model.dto;

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

	/**
	 * @return the producId
	 */
	public String getProducId() {
		return producId;
	}

	/**
	 * @param producId the producId to set
	 */
	public void setProducId(String producId) {
		this.producId = producId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the qtyInStock
	 */
	public Integer getQtyInStock() {
		return qtyInStock;
	}

	/**
	 * @param qtyInStock the qtyInStock to set
	 */
	public void setQtyInStock(Integer qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	/**
	 * @return the prodCategory
	 */
	public String getProdCategory() {
		return prodCategory;
	}

	/**
	 * @param prodCategory the prodCategory to set
	 */
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the prodManufacturer
	 */
	public String getProdManufacturer() {
		return prodManufacturer;
	}

	/**
	 * @param prodManufacturer the prodManufacturer to set
	 */
	public void setProdManufacturer(String prodManufacturer) {
		this.prodManufacturer = prodManufacturer;
	}

	/**
	 * @return the prodMfgDate
	 */
	public String getProdMfgDate() {
		return prodMfgDate;
	}

	/**
	 * @param prodMfgDate the prodMfgDate to set
	 */
	public void setProdMfgDate(String prodMfgDate) {
		this.prodMfgDate = prodMfgDate;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
