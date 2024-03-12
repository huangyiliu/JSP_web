package com.fs.tic.web_shopping.business.model.service;

import java.sql.SQLException;
import java.util.List;

import com.fs.tic.web_shopping.business.model.bean.CategoryBean;
import com.fs.tic.web_shopping.business.model.bean.ItemBean;
import com.fs.tic.web_shopping.business.model.dao.CategoryDAO;
import com.fs.tic.web_shopping.business.model.dao.ItemDAO;

/**
 * 業務サービスの実装クラスです。
 * 
 * <pre>
 *   店舗業務サービスを提供します。
 * </pre>
 */
public final class StoreService extends AbstractBusinessService {
	/** CategoryDAO */
	private final CategoryDAO daoCategory = new CategoryDAO();
	/** ItemDAO */
	private final ItemDAO daoItem = new ItemDAO();
	
	/** インスタンス */
	private static final StoreService instance = new StoreService();
	
	/**
	 * インスタンスを取得します。
	 * @return StoreService インスタンス
	 */
	public static StoreService getInstance() {
		return instance;
	}
	
	/**
	 * StoreService.
	 */
	private StoreService() {
	}
	
	/**
	 * 商品カテゴリ一覧を取得します。
	 * @return List<CategoryBean> 商品カテゴリリスト
	 * @throws SQLException データベースアクセス失敗時
	 */
	public List<CategoryBean> getCategoryList() throws SQLException {
		// 商品カテゴリマスタから全件を取得
		return daoCategory.selectAll();
	}
	
	/**
	 * 指定されたカテゴリコードの商品一覧を取得します。
	 * @param categoryCode カテゴリコード
	 * @return List<ItemBean> 商品リスト
	 * @throws SQLException データベースアクセス失敗時
	 */
	public List<ItemBean> getItemList(String categoryCode) throws SQLException {
		// 指定されたカテゴリコードの商品を取得
		return daoItem.selectByCategoryCode(categoryCode);
	}
	
	/**
	 * 対象の商品情報を取得します。
	 * @param itemCode 商品コード
	 * @return ItemBean 商品情報 - 存在しない場合は null
	 * @throws SQLException データベースアクセス失敗時
	 */
	public ItemBean getItem(String itemCode) throws SQLException {
		// 対象の商品情報を取得
		return daoItem.select(itemCode);
	}
}
