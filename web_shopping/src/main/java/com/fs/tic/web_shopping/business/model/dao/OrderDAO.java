package com.fs.tic.web_shopping.business.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fs.tic.web_shopping.business.model.Cart;
import com.fs.tic.web_shopping.business.model.OrderResult;
import com.fs.tic.web_shopping.business.model.bean.CustomerBean;
import com.fs.tic.web_shopping.business.model.bean.OrderedBean;
import com.fs.tic.web_shopping.business.model.bean.OrderedDetailBean;
import com.fs.tic.web_shopping.business.util.PropertyLoader;

/**
 * データアクセスオブジェクトの実装クラスです。
 * 
 * <pre>
 * 注文に関するデータベース操作を行います。
 *   [customer] 顧客情報
 *   [ordered] 注文情報
 *   [ordered_detail] 注文明細情報
 * </pre>
 */
public class OrderDAO extends AbstractDataAccessObject {
	/**
	 * 注文カート情報をデータベースに更新します。
	 * @param cart 注文カート情報
	 * @return OrderResult 注文結果
	 * @throws SQLException データベース更新失敗時
	 */
	public OrderResult update(Cart cart) throws SQLException {
		// コネクション取得
		Connection con = getConnection();
		// 注文情報更新処理
		try {
			// トランザクション管理 - 自動コミット解除
			con.setAutoCommit(false);
			// 顧客情報更新
			int customerNumber = updateCustomer(con, cart.getCustomer().toCustomerBean());
			// 注文情報更新
			int orderedNumber = updateOrdered(con, cart.toOrderedBean(customerNumber));
			// 注文明細情報更新
			updateOrderedDetail(con, cart.toOrderedDetailBeanList(orderedNumber));
			// トランザクション管理 - コミット
			con.commit();
			// 注文結果返却
			return new OrderResult(customerNumber, orderedNumber);
		} catch (Exception e) {
			e.printStackTrace();
			// トランザクション管理 - ロールバック
			con.rollback();
			// 例外を通知
			throw new SQLException(PropertyLoader.getProperty("message.order.update.failed"), e);
		} finally {
			// コネクションリリース
			releaseConnection(con);
		}
	}
	
	/**
	 * 顧客情報テーブルを更新します。
	 * @param con コネクション
	 * @param bean 顧客情報
	 * @return int 顧客番号
	 * @throws SQLException データベース更新失敗時
	 */
	private int updateCustomer(Connection con, CustomerBean bean) throws SQLException {
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO customer (customer_name, address, tel, email)");
		sql.append(" VALUES (?, ?, ?, ?)");
		// ステートメント
		PreparedStatement st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		// SQL パラメータ設定
		st.setString(1, bean.getCustomerName());
		st.setString(2, bean.getAddress());
		st.setString(3, bean.getTel());
		st.setString(4, bean.getEmail());
		// SQL 問い合わせ（更新）
		st.executeUpdate();
		// 自動採番された項目値を取得
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		// 顧客番号（自動採番）
		int customerNumber = rs.getInt(1);
		// クローズ
		rs.close();
		st.close();
		// 顧客番号返却
		return customerNumber;
	}
	
	/**
	 * 注文情報テーブルを更新します。
	 * @param con コネクション
	 * @param bean 注文情報
	 * @return int 注文番号
	 * @throws SQLException データベース更新失敗時
	 */
	private int updateOrdered(Connection con, OrderedBean bean) throws SQLException {
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ordered (customer_number, ordered_datetime, total_price)");
		sql.append(" VALUES (?, ?, ?)");
		// ステートメント
		PreparedStatement st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		// SQL パラメータ設定
		st.setInt(1, bean.getCustomerNumber());
		st.setTimestamp(2, toSQLTimestamp(bean.getOrderedDatetime()));
		st.setLong(3, bean.getTotalPrice());
		// SQL 問い合わせ（更新）
		st.executeUpdate();
		// 自動採番された項目値を取得
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		// 注文番号（自動採番）
		int orderedNumber = rs.getInt(1);
		// クローズ
		rs.close();
		st.close();
		// 注文番号返却
		return orderedNumber;
	}
	
	/**
	 * 注文明細情報テーブルを更新します。
	 * @param con コネクション
	 * @param list 注文明細情報リスト
	 * @throws SQLException データベース更新失敗時
	 */
	private void updateOrderedDetail(Connection con, List<OrderedDetailBean> list) throws SQLException {
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ordered_detail (ordered_number, ordered_detail_number, item_code, quantity, unit_price)");
		sql.append(" VALUES (?, ?, ?, ?, ?)");
		// ステートメント
		PreparedStatement st = con.prepareStatement(sql.toString());
		// 明細処理
		for (OrderedDetailBean bean : list) {
			// SQL パラメータ設定
			st.setInt(1, bean.getOrderedNumber());
			st.setInt(2, bean.getOrderedDetailNumber());
			st.setString(3, bean.getItemCode());
			st.setInt(4, bean.getQuantity());
			st.setInt(5, bean.getUnitPrice());
			// SQL 問い合わせ（更新）
			st.executeUpdate();
		}
		// クローズ
		st.close();
	}
}
