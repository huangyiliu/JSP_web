package com.fs.tic.web_shopping.business.controller.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fs.tic.web_shopping.business.model.service.CartService;
import com.fs.tic.web_shopping.business.util.EncodeUtility;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * 業務コントローラの実装クラスです。
 * 
 * <pre>
 * カート商品削除
 * </pre>
 */
public class BusinessControllerDeleteCartItem extends AbstractBusinessController {
	/**
	 * このコントローラが対応するアクション名を定義します。
	 * @return String アクション名
	 */
	@Override
	public String actionName() {
		return "delete_cart_item";
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
		// 商品コード
		String code = EncodeUtility.toUTF8(request.getParameter("item_code"));
		// カートから購入商品を削除
		CartService.getInstance().removeItem(request, code);
		// メニューステータス [カート情報]
		setMenuStatusCart(request);
		// 転送先 URL 返却
		return PropertyLoader.getProperty("url.jsp.cart");
	}
}
