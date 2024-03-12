package com.fs.tic.web_shopping.business.model.service;

import java.sql.SQLException;

import com.fs.tic.web_shopping.business.model.Cart;
import com.fs.tic.web_shopping.business.model.OrderResult;
import com.fs.tic.web_shopping.business.model.dao.OrderDAO;

/**
 * 業務サービスの実装クラスです。
 * 
 * <pre>
 *   注文に関するサービスを提供します。
 * </pre>
 */
public final class OrderService extends AbstractBusinessService {
	/** OrderDAO */
	private final OrderDAO daoOrder = new OrderDAO();
	
	/** インスタンス */
	private static final OrderService instance = new OrderService();
	
	/**
	 * インスタンスを取得します。
	 * @return OrderService インスタンス
	 */
	public static OrderService getInstance() {
		return instance;
	}
	
	/**
	 * OrderService.
	 */
	private OrderService() {
	}
	
	/**
	 * 注文確定処理を行います。
	 * @param cart 注文カート情報
	 * @return OrderResult 注文結果
	 * @throws SQLException データベース更新失敗時
	 */
	public OrderResult commitOrder(Cart cart) throws SQLException {
		// 注文情報更新
		OrderResult result = daoOrder.update(cart);
		// カート情報クリア(注文確定後)
		Cart c = cart.clear();
		// 注文カート情報を格納して、注文結果返却
		return result.newInstance(c);
	}
}
