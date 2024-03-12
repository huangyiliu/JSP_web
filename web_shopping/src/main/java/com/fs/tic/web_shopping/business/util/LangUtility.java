package com.fs.tic.web_shopping.business.util;

/**
 * 基本的なユーティリティクラスです。
 */
public final class LangUtility {
	/**
	 * LangUtility.
	 */
	private LangUtility() {
	}
	
	/**
	 * 数値文字列を int に変換します。
	 * @param s 数値文字列
	 * @param d デフォルト値
	 * @return int 変換結果
	 */
	public static int toInt(String s, int d) {
		try {
			// int に変換
			return Integer.parseInt(s);
		} catch (Exception e) {
			// デフォルト値
			return d;
		}
	}
	
	/**
	 * 数値文字列を long に変換します。
	 * @param s 数値文字列
	 * @param d デフォルト値
	 * @return long 変換結果
	 */
	public static long toLong(String s, long d) {
		try {
			// long に変換
			return Long.parseLong(s);
		} catch (Exception e) {
			// デフォルト値
			return d;
		}
	}
	
	/**
	 * 文字列が空か判断します。
	 * @param s 対象文字列
	 * @return boolean 判断結果 - 文字列が null 、または空であれば true
	 */
	public static boolean isEmpty(String s) {
		// null 判断
		if (s == null) {
			// 空扱い
			return true;
		}
		// 空文字判断
		return s.length() == 0;
	}
	
	/**
	 * 文字列配列が空か判断します。
	 * @param s 文字列配列
	 */
	public static boolean isEmpty(String[] s) {
		// null 判断
		if (s == null) {
			// 空扱い
			return true;
		}
		// 要素数判断
		return s.length == 0;
	}
	
	/**
	 * 対象文字列が null かどうか判断し、 null の場合は代替文字列を返却します。
	 * @param s 対象文字列
	 * @param r 代替文字列
	 * @return 対象文字列 - 対象が null の場合は代替文字列
	 */
	public static String nvl(String s, String r) {
		// null 判断
		if (s == null) {
			// 代替文字列返却
			return r;
		}
		// 対象文字列返却
		return s;
	}
}
