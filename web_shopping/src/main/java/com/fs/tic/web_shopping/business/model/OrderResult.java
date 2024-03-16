package com.fs.tic.web_shopping.business.model;

/**
 * 注文結果クラスです。
 */
public class OrderResult {
	/** 顧客番号 */
	private final int customerNumber;
	/** 注文番号 */
	private final int orderedNumber;
	/** 注文カート情報 */
	private final Cart orderedCart;

	/**
	 * OrderResult を構築します。
	 * @param customerNumber 顧客番号
	 * @param orderedNumber 注文番号
	 */
	public OrderResult(int customerNumber, int orderedNumber) {
		this(customerNumber, orderedNumber, null);
	}

	/**
	 * OrderResult を構築します。
	 * @param customerNumber 顧客番号
	 * @param orderedNumber 注文番号
	 * @param orderedCart 注文カート情報
	 */
	private OrderResult(int customerNumber, int orderedNumber, Cart orderedCart) {
		this.customerNumber = customerNumber;
		this.orderedNumber = orderedNumber;
		this.orderedCart = orderedCart;
	}

	public synchronized long getTotal() {
	    if (orderedCart != null) {
	        return orderedCart.getTotal();
	    } else {
	        return 0;
	    }
	}

	/**
	 * 注文カート情報を保持した新しいインスタンスを生成します。
	 * @param cart 注文カート情報
	 * @return OrderResult 注文カート情報を保持した新しいインスタンス
	 */
	public OrderResult newInstance(Cart cart) {
		// 新規インスタンス
		return new OrderResult(customerNumber, orderedNumber, cart);
	}

	/**
	 * 顧客番号を取得します。
	 * @return int 顧客番号
	 */
	public int getCustomerNumber() {
		// 顧客番号
		return customerNumber;
	}

	/**
	 * 注文番号を取得します。
	 * @return int 注文番号
	 */
	public int getOrderedNumber() {
		// 注文番号
		return orderedNumber;
	}

	/**
	 * 注文カート情報を取得します。
	 * @return Cart 注文カート情報
	 */
	public Cart getOrderedCart() {
		// 注文カート情報
		return orderedCart;
	}
}
