package com.fs.tic.web_shopping.business.model.bean;

/**
 * 注文明細情報 Bean
 */
public class OrderedDetailBean {
	/** 注文番号 */
	private int orderedNumber;
	/** 注文明細番号 */
	private int orderedDetailNumber;
	/** 商品コード */
	private String itemCode;
	/** 購入数量 */
	private int quantity;
	/** 購入単価 */
	private int unitPrice;
	
	/**
	 * 注文番号を設定します。
	 * @param orderedNumber 注文番号
	 */
	public void setOrderedNumber(int orderedNumber) {
		this.orderedNumber = orderedNumber;
	}
	
	/**
	 * 注文明細番号を設定します。
	 * @param orderedDetailNumber 注文明細番号
	 */
	public void setOrderedDetailNumber(int orderedDetailNumber) {
		this.orderedDetailNumber = orderedDetailNumber;
	}
	
	/**
	 * 商品コードを設定します。
	 * @param itemCode 商品コード
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	/**
	 * 購入数量を設定します。
	 * @param quantity 購入数量
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 購入単価を設定します。
	 * @param unitPrice 購入単価
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 注文番号を取得します。
	 * @return int 注文番号
	 */
	public int getOrderedNumber() {
		return orderedNumber;
	}
	
	/**
	 * 注文明細番号を取得します。
	 * @return int 注文明細番号
	 */
	public int getOrderedDetailNumber() {
		return orderedDetailNumber;
	}
	
	/**
	 * 商品コードを取得します。
	 * @return String 商品コード
	 */
	public String getItemCode() {
		return itemCode;
	}
	
	/**
	 * 購入数量を取得します。
	 * @return int 購入数量
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * 購入単価を取得します。
	 * @return int 購入単価
	 */
	public int getUnitPrice() {
		return unitPrice;
	}
}
