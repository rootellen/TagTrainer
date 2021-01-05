package com.example.tagandroid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart cart;
    private Map<String, CartProduct> products;


    private Cart() {
        products = new HashMap<>();
    }

    public static Cart getInstance() {
        if (cart == null)
            cart = new Cart();

        return cart;
    }

    public ArrayList<CartProduct> getCartProductsList() {
        return new ArrayList<>(products.values());
    }

    public void addToCart(Product p) {

        if (products.containsKey(p.getId())) {
            CartProduct product = products.get(p.getId());
            assert product != null;
            product.setQuantity(product.getQuantity() + 1);
        } else {
            products.put(p.getId(), new CartProduct(p));
        }
    }


    public void removeFromCart(CartProduct productClicked) {
        int productQuantity = productClicked.getQuantity();

        if (productQuantity == 1) {
            products.remove(productClicked.getProduct().getId());
        } else {
            productClicked.setQuantity(productQuantity - 1);
        }
    }

    public void clear() {
        products.clear();
    }


}


