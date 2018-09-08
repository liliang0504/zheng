package com.zheng.shop.common.constant;

import com.zheng.common.base.BaseResult;

/**
 * shop系统常量枚举类
 */
public class ShopResult extends BaseResult {

    public ShopResult(ShopResultConstant shopResultConstant, Object data) {
        super(shopResultConstant.getCode(), shopResultConstant.getMessage(), data);
    }

}
