package com.fs.tic.web_shopping.business.controller.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fs.tic.web_shopping.business.model.CartCustomer;
import com.fs.tic.web_shopping.business.model.service.CartService;
import com.fs.tic.web_shopping.business.util.EncodeUtility;
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
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// カート商品チェック
		if (!CartService.getInstance().hasItem(request)) {
			throw new IllegalStateException(PropertyLoader.getProperty("message.cart.empty"));
		}
		// 顧客情報を生成
		CartCustomer customer = new CartCustomer(
			EncodeUtility.toUTF8(request.getParameter("name")),
			EncodeUtility.toUTF8(request.getParameter("address")),
			EncodeUtility.toUTF8(request.getParameter("tel")),
			EncodeUtility.toUTF8(request.getParameter("email"))
		);
		// 顧客情報をカートに設定
		CartService.getInstance().setCustomer(request, customer);
		// メニューステータス [カート情報]
		setMenuStatusCart(request);
		// 転送先 URL 返却
		return PropertyLoader.getProperty("url.jsp.confirm_order");
	}
}
