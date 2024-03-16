package com.fs.tic.web_shopping.business.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * データアクセスオブジェクトの基底クラスです。
 */
public abstract class AbstractDataAccessObject {
	/**
	 * コネクションを取得します。
	 * @return Connection コネクション
	 * @throws SQLException SQL 例外
	 */
	protected Connection getConnection() throws SQLException {
		// コネクション取得
		return ConnectionManager.getInstance().getConnection();
	}

	/**
	 * コネクションをリリースします。
	 * @param con コネクション
	 */
	protected void releaseConnection(Connection con) {
		// コネクションリリース
		ConnectionManager.getInstance().releaseConnection(con);
	}

	/**
	 * java.util.Date を java.sql.Timestamp に変換します。
	 * @param date Date
	 * @return Timestamp 変換オブジェクト
	 */
	protected Timestamp toSQLTimestamp(Date date) {
		// 変換
		return new Timestamp(date.getTime());
	}
}
