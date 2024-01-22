package me.feathers.online.cart.service;

import java.util.Map;

import me.feathers.online.entity.OrderItem;

/**
 * 购物车相关操作
 */
public interface IShoppingCart {
    // 添加到购物车
    void addCart(OrderItem item);

	// 清空购物车
	void clear();

	// 修改数量
	void modifyCount(Long bookId, int count);

	// 删除某个条目
	void delete(Long bookId);

	// 获取总价
	double getTotal();

	// 获取商品数量
	int getCount();
	
	//获取购物车数据
	Map<Long, OrderItem> getItems();
	
	//获取购物车的商品详情
	OrderItem find(Long bookId);
	
}