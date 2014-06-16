package com.vito16.shop.util;

import com.vito16.shop.common.Constants;
import com.vito16.shop.model.Product;
import com.vito16.shop.model.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车工具类
 *
 * @author Vito
 * @version 2014/6/16
 */
public class CartUtil {
    public static final String CART = Constants.CART;

    /**
     * 添加商品到购物车中
     *
     * @param session
     * @param product
     * @param total
     */
    public static synchronized void saveProductToCart(HttpSession session, Product product, Integer total) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        CartItem ci = new CartItem(product, total);
        if (cartItemMap == null) {
            cartItemMap = new HashMap < Integer, CartItem > ();
        }
        //判断当前购物车中是否包含此商品
        if (cartItemMap.containsKey(product.getId())) {
            CartItem currentCi = cartItemMap.get(product.getId());
            currentCi.setTotal(currentCi.getTotal() + total);
            cartItemMap.put(product.getId(), currentCi);
        } else {
            cartItemMap.put(product.getId(), ci);
        }
        session.setAttribute(CART, cartItemMap);
    }

    /**
     * 删除购物车中的商品
     *
     * @param session
     * @param product
     */
    public static synchronized void deleteProductFromCart(HttpSession session, Product product) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        cartItemMap.remove(product.getId());
        session.setAttribute(CART, cartItemMap);
    }

    /**
     * 清空购物车
     *
     * @param session
     * @param product
     */
    public static synchronized void cleanCart(HttpSession session) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        cartItemMap.clear();
        session.setAttribute(CART, cartItemMap);
    }
}
