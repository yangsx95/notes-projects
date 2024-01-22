package me.feathers.online.cart.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import me.feathers.online.cart.service.IShoppingCart;
import me.feathers.online.entity.OrderItem;

/**
 * 购物车
 *
 * @author Feathers
 * @create 2017-06-30-10:33
 */
public class MemoryCart implements IShoppingCart {

    private Map<Long, OrderItem> items = new HashMap<>();
    private double total; //总费用

    @Override
    public void addCart(OrderItem item) {
        if (item != null) {
            items.put(item.getItemId(), item);
            updateTotal();
        }
    }

    @Override
    public void clear() {
        items.clear();
        updateTotal();
    }

    @Override
    public void modifyCount(Long bookId, int count) {
        OrderItem item = items.get(bookId);
        item.setCount(count);
        updateTotal();
    }

    @Override
    public void delete(Long bookId) {
        if (bookId != null) {
            items.remove(bookId);
            updateTotal();
        }
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Map<Long, OrderItem> getItems() {
        return items;
    }

    @Override
    public OrderItem find(Long bookId) {
        return items.get(bookId);
    }

    // 更新total
    private void updateTotal(){
        Set<Long> item = items.keySet();
        Iterator<Long> iterator = item.iterator();
        double tt = 0;
        while (iterator.hasNext()) {
            Long next = iterator.next();
            OrderItem item1 = items.get(next);
            // 保留两位小数
            BigDecimal b = new BigDecimal(item1.getPrice() * item1.getCount());
            tt += b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        this.total = tt;
    }
}
