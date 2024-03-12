package com.fs.tic.web_shopping.business.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fs.tic.web_shopping.business.model.Cart;
import com.fs.tic.web_shopping.business.model.CartCustomer;
import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * 業務サービスの実装クラスです。
 * 
 * <pre>
 *   カート操作に関するサービスを提供します。
 * </pre>
 */
public final class CartService extends AbstractBusinessService {
	/** インスタンス */
	private static final CartService instance = new CartService();
	
	/**
	 * インスタンスを取得します。
	 * @return CartService インスタンス
	 */
	public static CartService getInstance() {
		return instance;
	}
	
	/**
	 * CartService.
	 */
	private CartService() {
	}
	
	/**
	 * 注文を確定するために、カートをチェックアウトします。
	 * @param request HttpServletRequest
	 * @return Cart カート
	 * @throws IllegalStateException - チェックアウトできない状態
	 */
	public Cart checkout(HttpServletRequest request) {
		// カート商品チェック
		if (!hasItem(request)) {
			// カート商品なし
			throw new IllegalStateException(PropertyLoader.getProperty("message.cart.empty"));
		}
		// 顧客情報チェック
		if (!hasCustomer(request)) {
			// 顧客情報なし
			throw new IllegalStateException(PropertyLoader.getProperty("message.customer.empty"));
		}
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, true);
		// カート返却
		return cart.checkout();
	}
	
	/**
	 * 購入商品をカートに追加します。
	 * @param request HttpServletRequest
	 * @param item 購入商品
	 * @param quantity 購入数量
	 */
	public void addItem(HttpServletRequest request, ItemBean item, int quantity) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, true);
		// 購入商品を追加
		cart.addItem(item, quantity);
	}
	
	/**
	 * カートから購入商品を削除します。
	 * @param request HttpServletRequest
	 * @param itemCode 商品コード
	 */
	public void removeItem(HttpServletRequest request, String itemCode) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, false);
		// カート有無判定
		if (cart == null) {
			// カートがなければ処理終了
			return;
		}
		// 購入商品を削除
		cart.removeItem(itemCode);
	}
	
	/**
	 * カートに商品が登録されているか判断します。
	 * @param request HttpServletRequest
	 * @return boolean 判断結果 - カートに商品が登録されている場合は true
	 */
	public boolean hasItem(HttpServletRequest request) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, false);
		// カート有無判定
		if (cart == null) {
			// カートなし
			return false;
		}
		// カート商品有無判断
		return cart.hasItem();
	}
	
	/**
	 * 顧客情報を設定します。
	 * @param request HttpServletRequest
	 * @param customer 顧客情報
	 */
	public void setCustomer(HttpServletRequest request, CartCustomer customer) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, true);
		// 顧客情報設定
		cart.setCustomer(customer);
	}
	
	/**
	 * 顧客情報を取得します。
	 * @param request HttpServletRequest
	 * @return CartCustomer 顧客情報 - 存在しない場合は null
	 */
	public CartCustomer getCustomer(HttpServletRequest request) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, false);
		// カート有無判定
		if (cart == null) {
			// 顧客情報なし
			return null;
		}
		// 顧客情報返却
		return cart.getCustomer();
	}
	
	/**
	 * 顧客情報を保持しているか判断します。
	 * @param request HttpServletRequest
	 * @return boolean 判断結果 - 顧客情報を保持している場合は true
	 */
	public boolean hasCustomer(HttpServletRequest request) {
		// ショッピングカートを取得
		Cart cart = getSessionCart(request, false);
		// カート有無判定
		if (cart == null) {
			// カートなし
			return false;
		}
		// 顧客情報有無
		return cart.hasCustomer();
	}
	
	/**
	 * セッションからショッピングカートを取得します。
	 * @param request HttpServletRequest
	 * @param create ショッピングカートが存在しない場合に新規作成する場合は true
	 * @return Cart ショッピングカート - ショッピングカートが存在せず、新規作成しない場合は null
	 */
	private Cart getSessionCart(HttpServletRequest request, boolean create) {
		// セッション取得
		HttpSession session = request.getSession(create);
		// セッション有無判断
		if (session == null) {
			return null;
		}
		// セッションからショッピングカートを取得
		Cart cart = (Cart)session.getAttribute("cart");
		// ショッピングカート有無判断
		if (cart == null && create) {
			// ショッピングカートが存在しなければ新規作成
			cart = new Cart();
			// セッションに登録
			session.setAttribute("cart", cart);
		}
		// ショッピングカート返却
		return cart;
	}
}
