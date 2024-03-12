package com.fs.tic.web_shopping.business.util;

import java.io.UnsupportedEncodingException;

/**
 * エンコードユーティリティクラスです。
 */
public final class EncodeUtility {
	/**
	 * EncodeUtility.
	 */
	private EncodeUtility() {
	}
	
	/**
	 * 文字列の文字コードを latin1 から UTF-8 に変換します。
	 * @param latin1 文字列（latin1）
	 * @return String 文字列（UTF-8） - 変換に失敗した場合は元の文字列
	 */
	public static String toUTF8(String latin1) {
		try {
			// null チェック
			if (latin1 == null) {
				return null;
			}
			// 文字コード変換
			return new String(latin1.getBytes("ISO_8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// 変換失敗
			return latin1;
		}
	}
}
