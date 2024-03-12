package com.fs.tic.web_shopping.business.controller.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.model.service.StoreService;
import com.fs.tic.web_shopping.business.util.EncodeUtility;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * 業務コントローラの実装クラスです。
 * 
 * <pre>
 * 商品カテゴリ選択
 * </pre>
 */
public class BusinessControllerSelectCategory extends AbstractBusinessController {
	/**
	 * このコントローラが対応するアクション名を定義します。
	 * @return String アクション名
	 */
	@Override
	public String actionName() {
		return "select_category";
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
		// カテゴリコード
		String code = EncodeUtility.toUTF8(request.getParameter("code"));
		// 商品一覧を取得
		List<ItemBean> list = StoreService.getInstance().getItemList(code);
		// 商品一覧を格納
		request.setAttribute("items", list);
		// メニューステータス [メニュー選択（商品カテゴリ）]
		setMenuStatusCurrentCategory(request, code);
		// 転送先 URL 返却
		return PropertyLoader.getProperty("url.jsp.item_list");
	}
}
