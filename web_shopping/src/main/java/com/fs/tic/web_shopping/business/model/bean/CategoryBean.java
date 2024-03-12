package com.fs.tic.web_shopping.business.model.bean;

/**
 * 商品カテゴリマスタ Bean
 */
public class CategoryBean {
	/** カテゴリコード */
	private String categoryCode;
	/** カテゴリ名 */
	private String categoryName;
	
	/**
	 * カテゴリコードを設定します。
	 * @param categoryCode カテゴリコード
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * カテゴリ名を設定します。
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * カテゴリコードを取得します。
	 * @return String カテゴリコード
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	
	/**
	 * カテゴリ名を取得します。
	 * @return String カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}
}
