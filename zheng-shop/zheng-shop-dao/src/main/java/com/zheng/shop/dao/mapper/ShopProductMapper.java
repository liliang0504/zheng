package com.zheng.shop.dao.mapper;

import com.zheng.shop.dao.model.ShopProduct;
import com.zheng.shop.dao.model.ShopProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopProductMapper {
    long countByExample(ShopProductExample example);

    int deleteByExample(ShopProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopProduct record);

    int insertSelective(ShopProduct record);

    List<ShopProduct> selectByExample(ShopProductExample example);

    ShopProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopProduct record, @Param("example") ShopProductExample example);

    int updateByExample(@Param("record") ShopProduct record, @Param("example") ShopProductExample example);

    int updateByPrimaryKeySelective(ShopProduct record);

    int updateByPrimaryKey(ShopProduct record);
}