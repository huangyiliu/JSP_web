package com.fs.tic.web_shopping.business.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.model.bean.OrderedBean;
import com.fs.tic.web_shopping.business.model.bean.OrderedDetailBean;

/**
 * ショッピングカートクラスです。
 */
public class Cart {
	/** 商品一覧 */
	private final LinkedHashMap<String, CartItem> itemList = new LinkedHashMap<>();
	/** 顧客情報 */
	private CartCustomer customer = null;
	/** 購入日時 */
	private Date orderedDatetime = new Date();
	
	/**
	 * カートをクリアします。
	 * @return Cart クリア前の情報を保持したカート
	 */
	public synchronized Cart clear() {
		// クリア前のカート
		Cart c = clone();
		// 商品一覧
		itemList.clear();
		// カート顧客
		customer = null;
		// 購入日時
		orderedDatetime = new Date();
		// クリア前のカート返却
		return c;
	}
	
	/**
	 * 注文を確定するために、カートをチェックアウトします。
	 * @return Cart 自身のインスタンス
	 */
	public synchronized Cart checkout() {
		// 購入日時更新
		orderedDatetime = new Date();
		// 自身を返却
		return this;
	}
	
	/**
	 * 購入商品をカートに追加します。
	 * @param item 購入商品
	 * @param quantity 購入数量
	 */
	public synchronized void addItem(ItemBean item, int quantity) {
		// 登録済み商品
		CartItem cartItem = itemList.get(item.getItemCode());
		// カートに存在しない場合
		if (cartItem == null) {
			// カート商品情報を生成
			cartItem = new CartItem(item);
			// リストに追加
			itemList.put(cartItem.getItemCode(), cartItem);
		}
		// 購入数量
		cartItem.setQuantity(cartItem.getQuantity() + quantity);
	}
	
	/**
	 * カートから商品を削除します。
	 * @param itemCode 商品コード
	 */
	public synchronized void removeItem(String itemCode) {
		// 対象商品を削除
		itemList.remove(itemCode);
	}
	
	/**
	 * カートに商品が登録されているか判断します。
	 * @return boolean 判断結果 - カートに商品が登録されている場合は true
	 */
	public synchronized boolean hasItem() {
		// 商品登録有無
		return !itemList.isEmpty();
	}
	
	/**
	 * 表示用の商品一覧参照です。
	 * @return Map<String, CartItem> 表示用の商品一覧参照
	 */
	@SuppressWarnings("unchecked")
	public synchronized Map<String, CartItem> getItemListForReference() {
		// 複製を返却
		return (Map<String, CartItem>)itemList.clone();
	}
	
	/**
	 * カート合計金額を取得します。
	 * @return long カート合計金額
	 */
	public synchronized long getTotal() {
		long total = 0;
		// 明細合計
		for (CartItem item : itemList.values()) {
			total += item.getTotal();
		}
		// 合計金額返却
		return total;
	}
	
	/**
	 * 顧客情報を設定します。
	 * @param customer 顧客情報
	 */
	public void setCustomer(CartCustomer customer) {
		// 顧客情報設定
		this.customer = customer;
	}
	
	/**
	 * 顧客情報を取得します。
	 * @return CartCustomer 顧客情報 - 存在しない場合は null
	 */
	public CartCustomer getCustomer() {
		// 顧客情報返却
		return customer;
	}
	
	/**
	 * 顧客情報を保持しているか判断します。
	 * @return boolean 判断結果 - 顧客情報を保持している場合は true
	 */
	public boolean hasCustomer() {
		// 顧客情報有無
		return customer != null;
	}
	
	/**
	 * OrderedBean 表現にします。
	 * @param customerNumber 顧客番号
	 * @return OrderedBean 注文情報
	 */
	public synchronized OrderedBean toOrderedBean(int customerNumber) {
		// 注文情報
		OrderedBean bean = new OrderedBean();
		// 注文情報設定
		bean.setCustomerNumber(customerNumber);
		bean.setOrderedDatetime(orderedDatetime);
		bean.setTotalPrice(getTotal());
		// 注文情報返却
		return bean;
	}
	
	/**
	 * OrderedDetailBean リスト表現にします。
	 * @param orderedNumber 注文番号
	 * @return List<OrderedDetailBean> 注文明細情報リスト
	 */
	public synchronized List<OrderedDetailBean> toOrderedDetailBeanList(int orderedNumber) {
		// 注文明細情報リスト
		List<OrderedDetailBean> list = new ArrayList<>();
		// 注文明細情報リスト設定
		for (CartItem item : itemList.values()) {
			// 注文明細情報
			OrderedDetailBean bean = item.toOrderedDetailBean(orderedNumber, list.size() + 1);
			// 注文明細情報追加
			list.add(bean);
		}
		// 注文明細情報リスト返却
		return list;
	}
	
	/**
	 * クローンを生成します。
	 * @return Cart クローン
	 */
	@Override
	protected Cart clone() {
		// 新規インスタンス
		Cart c = new Cart();
		// 各項目を設定
		c.itemList.putAll(itemList);
		c.customer = customer;
		c.orderedDatetime = orderedDatetime;
		// クローンを返却
		return c;
	}
}
