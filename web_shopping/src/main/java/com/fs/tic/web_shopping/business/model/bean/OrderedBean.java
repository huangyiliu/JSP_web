package com.fs.tic.web_shopping.business.model.bean;

import java.util.Date;

/**
 * 注文情報 Bean
 */
public class OrderedBean {
	/** 注文番号 */
	private int orderedNumber;
	/** 顧客番号 */
	private int customerNumber;
	/** 購入日時 */
	private Date orderedDatetime;
	/** 合計金額 */
	private long totalPrice;
	
	/**
	 * 注文番号を設定します。
	 * @param orderedNumber 注文番号
	 */
	public void setOrderedNumber(int orderedNumber) {
		this.orderedNumber = orderedNumber;
	}
	
	/**
	 * 顧客番号を設定します。
	 * @param customerNumber 顧客番号
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	/**
	 * 購入日時を設定します。
	 * @param orderedDatetime 購入日時
	 */
	public void setOrderedDatetime(Date orderedDatetime) {
		this.orderedDatetime = orderedDatetime;
	}
	
	/**
	 * 合計金額を設定します。
	 * @param totalPrice 合計金額
	 */
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * 注文番号を取得します。
	 * @return int 注文番号
	 */
	public int getOrderedNumber() {
		return orderedNumber;
	}
	
	/**
	 * 顧客番号を取得します。
	 * @return int 顧客番号
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	/**
	 * 購入日時を取得します。
	 * @return Date 購入日時
	 */
	public Date getOrderedDatetime() {
		return orderedDatetime;
	}
	
	/**
	 * 合計金額を取得します。
	 * @return long 合計金額
	 */
	public long getTotalPrice() {
		return totalPrice;
	}
}
