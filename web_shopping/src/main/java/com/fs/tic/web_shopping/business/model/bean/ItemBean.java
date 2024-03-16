package com.fs.tic.web_shopping.business.model.bean;

/**
 * 商品マスタ Bean
 */
public class ItemBean {
	/** 商品コード */
	private String itemCode;
	/** カテゴリコード */
	private String categoryCode;
	/** 商品名 */
	private String itemName;
	/** 単価 */
	private int itemPrice;
	/** 画像 */
	private String imagePath;

	/**
	 * 商品コードを設定します。
	 * @param itemCode 商品コード
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * カテゴリコードを設定します。
	 * @param categoryCode カテゴリコード
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * 商品名を設定します。
	 * @param itemName 商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 単価を設定します。
	 * @param itemPrice 単価
	 */
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * imageを設定します。
	 * @param imagePath 画像
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 商品コードを取得します。
	 * @return String 商品コード
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * カテゴリコードを取得します。
	 * @return String カテゴリコード
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * 商品名を取得します。
	 * @return String 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 単価を取得します。
	 * @return int 単価
	 */
	public int getItemPrice() {
		return itemPrice;
	}
	/**
	 * 単価を取得します。
	 * @return int 単価
	 */
	public String getImagePath() {
		return imagePath;
	}
}
