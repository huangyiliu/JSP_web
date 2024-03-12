package com.fs.tic.web_shopping.business.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fs.tic.web_shopping.business.controller.control.IBusinessController;
import com.fs.tic.web_shopping.business.model.bean.CategoryBean;
import com.fs.tic.web_shopping.business.model.service.StoreService;
import com.fs.tic.web_shopping.business.util.EncodeUtility;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * Servlet implementation class ShoppingServlet
 */
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** 業務コントローラ一覧 */
	private final Map<String, IBusinessController> controllers = BusinessControllerFactory.createControllers();
	
	/**
	 * サーブレット初期化処理を行います。
	 * @throws ServletException サーブレット例外
	 */
	@Override
	public void init() throws ServletException {
		// アプリケーションスコープに商品カテゴリ一覧を属性として格納
		try {
			// 商品カテゴリ一覧を取得
			List<CategoryBean> list = StoreService.getInstance().getCategoryList();
			// アプリケーションスコープに格納
			getServletContext().setAttribute("categories", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// コントローラ呼び出し
		dispatchController(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// コントローラ呼び出し
		dispatchController(request, response);
	}
	
	/**
	 * 対象コントローラの処理を呼び出します。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException サーブレット例外
	 * @throws IOException 入出力例外
	 */
	private void dispatchController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 業務コントローラ呼び出し
		try {
			// リクエストアクション
			String action = EncodeUtility.toUTF8(request.getParameter("action"));
			// 対象の業務コントローラが存在しない場合は例外
			if (!controllers.containsKey(action)) {
				throw new IllegalArgumentException(PropertyLoader.getProperty("message.illegal.action") + "（action=" + action + "）");
			}
			// 対象の業務コントローラを取得
			IBusinessController c = controllers.get(action);
			// 業務コントローラ処理
			String url = c.service(request, response);
			// 処理転送（表示ページ）
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// エラーメッセージをリクエストに格納
			request.setAttribute("errorMessage", e.getMessage());
			// 処理転送（エラーページ）
			RequestDispatcher dispatcher = request.getRequestDispatcher(PropertyLoader.getProperty("url.jsp.error"));
			dispatcher.forward(request, response);
		}
	}
}
