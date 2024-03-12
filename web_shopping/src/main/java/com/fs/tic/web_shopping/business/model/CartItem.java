package com.fs.tic.web_shopping.business.model;

import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.model.bean.OrderedDetailBean;

/**
 * ショッピングカート商品クラスです。
 */
public class CartItem {
	/** 商品情報 */
	private final ItemBean item;
	/** 購入数量 */
	private int quantity;
	
	/**
	 * CartItem を構築します。
	 * @param item 商品情報
	 */
	public CartItem(ItemBean item) {
		this.item = item;
	}
	
	/**
	 * 購入数量を設定します。
	 * @param quantity 購入数量
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 商品コードを取得します。
	 * @return String 商品コード
	 */
	public String getItemCode() {
		return item.getItemCode();
	}
	
	/**
	 * カテゴリコードを取得します。
	 * @return String カテゴリコード
	 */
	public String getCategoryCode() {
		return item.getCategoryCode();
	}
	
	/**
	 * 商品名を取得します。
	 * @return String 商品名
	 */
	public String getItemName() {
		return item.getItemName();
	}
	
	/**
	 * 単価を取得します。
	 * @return int 単価
	 */
	public int getItemPrice() {
		return item.getItemPrice();
	}
	
	/**
	 * 購入数量を取得します。
	 * @return int 購入数量
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * 購入合計金額を取得します。
	 * @return int 購入合計金額
	 */
	public int getTotal() {
		// 単価 × 購入数量
		return getItemPrice() * getQuantity();
	}
	
	/**
	 * OrderedDetailBean 表現にします。
	 * @param orderedNumber 注文番号
	 * @param orderedDetailNumber 注文明細番号
	 * @return OrderedDetailBean 注文明細情報
	 */
	public OrderedDetailBean toOrderedDetailBean(int orderedNumber, int orderedDetailNumber) {
		// 注文明細情報
		OrderedDetailBean bean = new OrderedDetailBean();
		// 注文明細情報設定
		bean.setOrderedNumber(orderedNumber);
		bean.setOrderedDetailNumber(orderedDetailNumber);
		bean.setItemCode(item.getItemCode());
		bean.setQuantity(quantity);
		bean.setUnitPrice(item.getItemPrice());
		// 注文明細情報返却
		return bean;
	}
}
