package com.fs.tic.web_shopping.business.controller.control;

import javax.servlet.http.HttpServletRequest;

/**
 * 業務コントローラの基底クラスです。
 */
public abstract class AbstractBusinessController implements IBusinessController {
	/**
	 * このコントローラが対応するアクション名を定義します。
	 * @return String アクション名
	 */
	public abstract String actionName();
	
	/**
	 * メニューステータス [TOP ページ] を設定します。
	 * @param request HttpServletRequest
	 */
	protected void setMenuStatusTop(HttpServletRequest request) {
		// メニューステータス（TOP ページ）
		request.setAttribute("menu_status_top", true);
	}
	
	/**
	 * メニューステータス [カート情報] を設定します。
	 * @param request HttpServletRequest
	 */
	protected void setMenuStatusCart(HttpServletRequest request) {
		// メニューステータス（カート情報）
		request.setAttribute("menu_status_cart", true);
	}
	
	/**
	 * メニューステータス [メニュー選択（商品カテゴリ）] を設定します。
	 * @param request HttpServletRequest
	 * @param code 商品カテゴリコード
	 */
	protected void setMenuStatusCurrentCategory(HttpServletRequest request, String code) {
		// メニュー選択（商品カテゴリ）
		request.setAttribute("menu_current_category", code);
	}
}
