package com.fs.tic.web_shopping.business.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * データベース接続管理クラスです。
 */
public final class ConnectionManager {
	/** データソース */
	private final DataSource source;
	
	/** インスタンス */
	private static final ConnectionManager instance = new ConnectionManager();
	
	/**
	 * インスタンスを取得します。
	 * @return ConnectionManager インスタンス
	 */
	public static ConnectionManager getInstance() {
		return instance;
	}
	
	/**
	 * ConnectionManager を構築します。
	 */
	private ConnectionManager() {
		// データソース検索（java:comp/env/{server.xml で指定した名前}）
		source = lookupDataSource("java:comp/env/jdbc/datasource");
	}
	
	/**
	 * データソースを検索します。
	 * @param name データソースの名前
	 * @return DataSource データソース - 失敗時は null
	 */
	private DataSource lookupDataSource(String name) {
		try {
			InitialContext context = new InitialContext();
			// データソース返却
			return (DataSource)context.lookup(name);
		} catch (NamingException e) {
			e.printStackTrace();
			// データソース取得失敗
			return null;
		}
	}
	
	/**
	 * コネクションを取得します。
	 * @return Connection コネクション
	 * @throws SQLException SQL 例外
	 */
	public Connection getConnection() throws SQLException {
		// データソース判定
		if (source == null) {
			throw new SQLException("データソースが null です。");
		}
		// コネクション返却
		return source.getConnection();
	}
	
	/**
	 * コネクションをリリースします。
	 * @param con コネクション
	 */
	public void releaseConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
