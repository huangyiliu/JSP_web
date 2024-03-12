package com.fs.tic.web_shopping.business.controller;

import java.util.HashMap;
import java.util.Map;

import com.fs.tic.web_shopping.business.controller.control.AbstractBusinessController;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerAddCartItem;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerConfirmOrder;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerDeleteCartItem;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerRequestCustomerInfo;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerRequestOrder;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerSelectCategory;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerShowCart;
import com.fs.tic.web_shopping.business.controller.control.BusinessControllerTop;
import com.fs.tic.web_shopping.business.controller.control.IBusinessController;

/**
 * 業務コントローラ生成クラスです。
 */
public final class BusinessControllerFactory {
	/**
	 * BusinessControllerFactory.
	 */
	private BusinessControllerFactory() {
	}
	
	/**
	 * 業務コントローラ一覧を生成します。
	 * @return Map<String, IBusinessController> 業務コントローラ一覧
	 */
	public static Map<String, IBusinessController> createControllers() {
		// 業務コントローラ一覧マップ
		Map<String, IBusinessController> map = new HashMap<>();
		// 各業務コントローラを登録
		putController(map, new BusinessControllerTop());
		putController(map, new BusinessControllerSelectCategory());
		putController(map, new BusinessControllerAddCartItem());
		putController(map, new BusinessControllerDeleteCartItem());
		putController(map, new BusinessControllerShowCart());
		putController(map, new BusinessControllerRequestCustomerInfo());
		putController(map, new BusinessControllerConfirmOrder());
		putController(map, new BusinessControllerRequestOrder());
		// 業務コントローラ一覧を返却
		return map;
	}
	
	/**
	 * アクション名をキーにして、業務コントローラをマップに登録します。
	 * @param map マップ
	 * @param controller 業務コントローラ
	 */
	private static void putController(Map<String, IBusinessController> map, AbstractBusinessController controller) {
		// アクション名をキーにして、コントローラを登録
		map.put(controller.actionName(), controller);
	}
}
