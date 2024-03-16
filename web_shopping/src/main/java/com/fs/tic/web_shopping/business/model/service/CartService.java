package com.fs.tic.web_shopping.business.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fs.tic.web_shopping.business.model.Cart;
import com.fs.tic.web_shopping.business.model.CartCustomer;
import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.util.LangUtility;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * カートサービスの実装クラスです。
 *
 * <pre>
 *   カート操作に関するサービスを提供します。
 * </pre>
 */
public final class CartService extends AbstractBusinessService {
	private static final CartService instance = new CartService();

	public static CartService getInstance() {
		return instance;
	}

	private CartService() {
	}

	public Cart checkout(HttpServletRequest request) {
		if (!hasItem(request)) {
			throw new IllegalStateException(PropertyLoader.getProperty("message.cart.empty"));
		}
		if (!hasCustomer(request)) {
			throw new IllegalStateException(PropertyLoader.getProperty("message.customer.empty"));
		}
		Cart cart = getSessionCart(request, true);
		return cart.checkout();
	}

	public void addItem(HttpServletRequest request, ItemBean item, int quantity) {
		Cart cart = getSessionCart(request, true);
		cart.addItem(item, quantity);
	}

	public void removeItem(HttpServletRequest request, String itemCode) {
		Cart cart = getSessionCart(request, false);
		if (cart != null) {
			cart.removeItem(itemCode);
		}
	}

	public boolean hasItem(HttpServletRequest request) {
		Cart cart = getSessionCart(request, false);
		return cart != null && cart.hasItem();
	}

	public void setCustomer(HttpServletRequest request, CartCustomer customer) {
		Cart cart = getSessionCart(request, true);
		cart.setCustomer(customer);
	}

	public CartCustomer getCustomer(HttpServletRequest request) {
		Cart cart = getSessionCart(request, false);
		return (cart != null) ? cart.getCustomer() : null;
	}

	public boolean hasCustomer(HttpServletRequest request) {
		Cart cart = getSessionCart(request, false);
		return (cart != null) && cart.hasCustomer();
	}

	private Cart getSessionCart(HttpServletRequest request, boolean create) {
		HttpSession session = request.getSession(create);
		if (session == null) {
			return null;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null && create) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}

	public static class CustomerResult {
		private CartCustomer customer;
		private Map<String, String> errors;

		public CustomerResult(CartCustomer customer, Map<String, String> errors) {
			this.customer = customer;
			this.errors = errors;
		}

		public CartCustomer getCustomer() {
			return customer;
		}

		public Map<String, String> getErrors() {
			return errors;
		}
	}

	public CartService.CustomerResult setCustomer(CartCustomer customer) {
		return checkCustomer(customer);
	}

	public String processCustomerResult(CartService.CustomerResult customerResult) {
		Map<String, String> errors = customerResult.getErrors();
		return (!errors.isEmpty()) ? "/errorPage.jsp" : "/confirm_order.jsp";
	}

	public CartService.CustomerResult checkCustomer(CartCustomer customer) {
		Map<String, String> result = new HashMap<>();

		if (LangUtility.isEmpty(customer.getCustomerName())) {
			result.put("name", "名前が入力されていません。");
		}

		if (LangUtility.isEmpty(customer.getAddress())) {
			result.put("address", "住所が入力されていません。");
		}

		String tel = customer.getTel();
		if (LangUtility.isEmpty(tel) || !tel.matches("\\d{10,11}")) {
			result.put("phoneNumber", "電話番号が入力されていません。");
		}

		String email = customer.getEmail();
		if (LangUtility.isEmpty(email) || !email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
			// 電子郵件未填入或格式不符合要求，可能需要返回錯誤消息或採取其他操作
		}

		return new CartService.CustomerResult(customer, result);
	}
}
