package com.fs.tic.web_shopping.business.model.bean;

/**
 * 顧客情報 Bean
 */
public class CustomerBean {
	/** 顧客番号 */
	private int customerNumber;
	/** 顧客名 */
	private String customerName;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String tel;
	/** メールアドレス */
	private String email;
	
	/**
	 * 顧客番号を設定します。
	 * @param customerNumber 顧客番号
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	/**
	 * 顧客名を設定します。
	 * @param customerName 顧客名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * 住所を設定します。
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 電話番号を設定します。
	 * @param tel 電話番号
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * メールアドレスを設定します。
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 顧客番号を取得します。
	 * @return int 顧客番号
	 */
	public int getCustomerNumber() {
		return customerNumber;
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
}
