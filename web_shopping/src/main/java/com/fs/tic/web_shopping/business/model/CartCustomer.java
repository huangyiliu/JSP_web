package com.fs.tic.web_shopping.business.model;

import com.fs.tic.web_shopping.business.model.bean.CustomerBean;
import com.fs.tic.web_shopping.business.util.LangUtility;

/**
 * ショッピングカート顧客クラスです。
 */
public class CartCustomer {
	/** 顧客名 */
	private final String customerName;
	/** 住所 */
	private final String address;
	/** 電話番号 */
	private final String tel;
	/** メールアドレス */
	private final String email;
	
	/**
	 * CartCustomer を構築します。
	 * @param customerName 顧客名
	 * @param address 住所
	 * @param tel 電話番号
	 * @param email メールアドレス
	 */
	public CartCustomer(String customerName, String address, String tel, String email) {
		this.customerName = customerName;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}
	
	/**
	 * 顧客名を取得します。
	 * @return String 顧客名
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * 住所を取得します。
	 * @return String 住所
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 電話番号を取得します。
	 * @return String 電話番号
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * メールアドレスを取得します。
	 * @return String メールアドレス
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * CustomerBean 表現にします。
	 * @return CustomerBean 顧客情報
	 */
	public CustomerBean toCustomerBean() {
		// 顧客情報
		CustomerBean bean = new CustomerBean();
		// 顧客情報設定
		bean.setCustomerName(LangUtility.isEmpty(customerName) ? "（未登録）" : customerName);
		bean.setAddress(address);
		bean.setTel(tel);
		bean.setEmail(LangUtility.isEmpty(email) ? "xxxx@xxxx.xxxx" : email);
		// 顧客情報返却
		return bean;
	}
}
