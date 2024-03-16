package com.fs.tic.web_shopping.business.controller.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fs.tic.web_shopping.business.model.CartCustomer;
import com.fs.tic.web_shopping.business.model.service.CartService;
import com.fs.tic.web_shopping.business.util.EncodeUtility;
import com.fs.tic.web_shopping.business.util.LangUtility;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * 業務コントローラの実装クラスです。
 *
 * <pre>
 * 注文確認
 * </pre>
 */
public class BusinessControllerConfirmOrder extends AbstractBusinessController {
	/**
	 * このコントローラが対応するアクション名を定義します。
	 * @return String アクション名
	 */
	@Override
	public String actionName() {
		return "confirm_order";
	}

	/**
	 * 業務コントローラ処理の実装です。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return String 転送先（forward）URL
	 * @throws ServletException サーブレット例外
	 * @throws IOException 入出力例外
	 * @throws SQLException データベースアクセス失敗時
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// カート商品チェック
		if (!CartService.getInstance().hasItem(request)) {
			throw new IllegalStateException(PropertyLoader.getProperty("message.cart.empty"));
		}

		// 驗證顧客資料
		CartCustomer customer = buildCustomerFromRequest(request);
		Map<String, String> errors = validateCustomerData(customer);

		if (!errors.isEmpty()) {
			// 驗證有誤，處理錯誤邏輯，這裡假設導向錯誤頁面
			request.setAttribute("errors", errors);
			return PropertyLoader.getProperty("url.jsp.customer_info");
		} else {
			// 顧客情報をカートに設定
			CartService.getInstance().setCustomer(request, customer);

			return PropertyLoader.getProperty("url.jsp.confirm_order");
		}
	}

	private Map<String, String> validateCustomerData(CartCustomer customer) {
		// 實際的驗證邏輯，返回驗證結果
		Map<String, String> errors = new HashMap<>();

		if (LangUtility.isEmpty(customer.getCustomerName())) {
			errors.put("name", PropertyLoader.getProperty("message.customername.empty"));
		}
		if (LangUtility.isEmpty(customer.getAddress())) {
			errors.put("address",PropertyLoader.getProperty("message.adress.empty"));
		}

		String tel = customer.getTel();
		if (LangUtility.isEmpty(tel)) {
			errors.put("tel", PropertyLoader.getProperty("message.tel.empty"));
		} else if (!tel.matches("\\d{10,11}") && !tel.matches("\\d+\\-\\d+\\-\\d+")) {
			errors.put("tel", PropertyLoader.getProperty("message.tel.error"));

		}

		String email = customer.getEmail();
		if (LangUtility.isEmpty(email)) {
			errors.put("email", PropertyLoader.getProperty("message.mail.empty"));
		} else if (!email.matches("[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+")) {
			errors.put("email", PropertyLoader.getProperty("message.mail.error"));

		}

		return errors;
	}

	private CartCustomer buildCustomerFromRequest(HttpServletRequest request) {
		// 從 request 中構建 CartCustomer 對象的邏輯，根據實際情況實現
		// 這裡的邏輯僅供參考，具體實現可能需要根據你的類型和需求進行調整
		return new CartCustomer(
				EncodeUtility.toUTF8(request.getParameter("name")),
				EncodeUtility.toUTF8(request.getParameter("address")),
				EncodeUtility.toUTF8(request.getParameter("tel")),
				EncodeUtility.toUTF8(request.getParameter("email")));
	}
}
