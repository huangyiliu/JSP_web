package com.fs.tic.web_shopping.business.util;

import java.util.ResourceBundle;

/**
 * プロパティファイルを読み込むクラスです。
 */
public final class PropertyLoader {
	/**
	 * PropertyLoader.
	 */
	private PropertyLoader() {
	}

	/**
	 * プロパティを取得します。
	 * @param name プロパティ名
	 * @return String プロパティ値
	 */
	public static String getProperty(String name) {
		// リソースを取得
		ResourceBundle resource = ResourceBundle.getBundle("application");
		// プロパティ値を返却
		// return resource.getString(name);

		// ※ Java9 から ResourceBundle のデフォルト文字コードが UTF-8 になったので、環境によってはエンコード不要
		// プロパティ値を返却（文字化けが発生する場合）
		return EncodeUtility.toUTF8(resource.getString(name));
	}
}
