package com.fs.tic.web_shopping.business.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fs.tic.web_shopping.business.model.bean.CategoryBean;

/**
 * データアクセスオブジェクトの実装クラスです。
 * 
 * <pre>
 *   [category] 商品カテゴリマスタ
 * </pre>
 */
public class CategoryDAO extends AbstractDataAccessObject {
	/**
	 * 商品カテゴリマスタから全件を取得します。
	 * @return List<CategoryBean> 商品カテゴリリスト
	 * @throws SQLException データベースアクセス失敗時
	 */
	public List<CategoryBean> selectAll() throws SQLException {
		// コネクション取得
		Connection con = getConnection();
		// 商品カテゴリマスタ情報取得処理
		try {
			// SQL
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT *");
			sql.append(" FROM category");
			sql.append(" ORDER BY category_code");
			// ステートメント
			PreparedStatement st = con.prepareStatement(sql.toString());
			// データベース問い合わせ
			ResultSet rs = st.executeQuery();
			// 商品カテゴリリスト
			List<CategoryBean> list = new ArrayList<>();
			// 問い合わせ結果から情報を取得
			while (rs.next()) {
				CategoryBean bean = new CategoryBean();
				// 各項目値を取得
				bean.setCategoryCode(rs.getString("category_code"));
				bean.setCategoryName(rs.getString("category_name"));
				// 商品カテゴリリストに追加
				list.add(bean);
			}
			// クローズ
			rs.close();
			st.close();
			// 商品カテゴリリスト返却
			return list;
		} finally {
			// コネクションリリース
			releaseConnection(con);
		}
	}
}
