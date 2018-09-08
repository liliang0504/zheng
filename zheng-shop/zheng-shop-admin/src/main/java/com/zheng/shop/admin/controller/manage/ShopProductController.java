package com.zheng.shop.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.common.base.BaseController;
import com.zheng.common.validator.LengthValidator;
import com.zheng.shop.common.constant.ShopResult;
import com.zheng.shop.common.constant.ShopResultConstant;
import com.zheng.shop.dao.model.ShopProduct;
import com.zheng.shop.dao.model.ShopProductExample;
import com.zheng.shop.rpc.api.ShopProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 */
@Controller
@Api(value = "商品管理", description = "商品管理")
@RequestMapping("/manage/product")
public class ShopProductController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopProductController.class);
	
	@Autowired
	private ShopProductService shopProductService;

	@ApiOperation(value = "商品首页")
	@RequiresPermissions("shop:product:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/product/index.jsp";
	}

	@ApiOperation(value = "商品列表")
	@RequiresPermissions("shop:product:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		ShopProductExample shopProductExample = new ShopProductExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			shopProductExample.setOrderByClause(sort + " " + order);
		}
		List<ShopProduct> rows = shopProductService.selectByExampleForOffsetPage(shopProductExample, offset, limit);
		long total = shopProductService.countByExample(shopProductExample);
		Map<String, Object> result = new HashMap<>(2);
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增商品")
	@RequiresPermissions("shop:product:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/product/create.jsp";
	}

	@ApiOperation(value = "新增商品")
	@RequiresPermissions("shop:product:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(ShopProduct shopProduct) {
		ComplexResult result = FluentValidator.checkAll()
				.on(shopProduct.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new ShopResult(ShopResultConstant.INVALID_LENGTH, result.getErrors());
		}
		int count = shopProductService.insertSelective(shopProduct);
		return new ShopResult(ShopResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除商品")
	@RequiresPermissions("shop:product:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = shopProductService.deleteByPrimaryKeys(ids);
		return new ShopResult(ShopResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改商品")
	@RequiresPermissions("shop:product:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		ShopProduct product = shopProductService.selectByPrimaryKey(id);
		modelMap.put("product", product);
		return "/manage/product/update.jsp";
	}

	@ApiOperation(value = "修改商品")
	@RequiresPermissions("shop:product:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, ShopProduct shopProduct) {
		ComplexResult result = FluentValidator.checkAll()
				.on(shopProduct.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new ShopResult(ShopResultConstant.INVALID_LENGTH, result.getErrors());
		}
		shopProduct.setId(id);
		int count = shopProductService.updateByPrimaryKeySelective(shopProduct);
		return new ShopResult(ShopResultConstant.SUCCESS, count);
	}

}