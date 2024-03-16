package com.fs.tic.web_shopping.business.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fs.tic.web_shopping.business.model.bean.ItemBean;

/**
 * データアクセスオブジェクトの実装クラスです。
 *
 * <pre>
 *   [item] 商品マスタ
 * </pre>
 */
public class ItemDAO extends AbstractDataAccessObject {
	/**
	 * 指定されたカテゴリコードの商品を取得します。
	 * @param categoryCode カテゴリコード
	 * @return List<ItemBean> 商品リスト
	 * @throws SQLException データベースアクセス失敗時
	 */
	public List<ItemBean> selectByCategoryCode(String categoryCode) throws SQLException {
		// コネクション取得
		Connection con = getConnection();
		// 商品マスタ情報取得処理
		try {
			// SQL
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT *");
			sql.append(" FROM item");
			sql.append(" WHERE category_code = ?");
			sql.append(" ORDER BY item_code");
			// ステートメント
			PreparedStatement st = con.prepareStatement(sql.toString());
			// パラメータ設定
			st.setString(1, categoryCode);
			// データベース問い合わせ
			ResultSet rs = st.executeQuery();
			// 商品リスト
			List<ItemBean> list = new ArrayList<>();
			// 問い合わせ結果から情報を取得
			while (rs.next()) {
				ItemBean bean = new ItemBean();
				// 各項目値を取得
				bean.setItemCode(rs.getString("item_code"));
				bean.setCategoryCode(rs.getString("category_code"));
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPrice(rs.getInt("item_price"));
				bean.setImagePath(rs.getString("image_path"));
				// 商品リストに追加
				list.add(bean);
			}
			// クローズ
			rs.close();
			st.close();
			// 商品リスト返却
			return list;
		} finally {
			// コネクションリリース
			releaseConnection(con);
		}
	}

	/**
	 * 対象の商品情報を取得します。
	 * @param itemCode 商品コード
	 * @return ItemBean 商品情報 - 存在しない場合は null
	 * @throws SQLException データベースアクセス失敗時
	 */
	public ItemBean select(String itemCode) throws SQLException {
		// コネクション取得
		Connection con = getConnection();
		// 商品マスタ情報取得処理
		try {
			// SQL
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT *");
			sql.append(" FROM item");
			sql.append(" WHERE item_code = ?");
			// ステートメント
			PreparedStatement st = con.prepareStatement(sql.toString());
			// パラメータ設定
			st.setString(1, itemCode);
			// データベース問い合わせ
			ResultSet rs = st.executeQuery();
			// 商品情報
			ItemBean bean = null;
			// 問い合わせ結果から情報を取得
			while (rs.next()) {
				bean = new ItemBean();
				// 各項目値を取得
				bean.setItemCode(rs.getString("item_code"));
				bean.setCategoryCode(rs.getString("category_code"));
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPrice(rs.getInt("item_price"));
				bean.setImagePath(rs.getString("image_path"));
			}
			// クローズ
			rs.close();
			st.close();
			// 商品情報返却
			return bean;
		} finally {
			// コネクションリリース
			releaseConnection(con);
		}
	}
}
