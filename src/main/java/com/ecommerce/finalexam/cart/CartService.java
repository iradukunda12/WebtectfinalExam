package com.ecommerce.finalexam.cart;

import com.ecommerce.finalexam.user.User;

import java.util.List;

public interface CartService {
    public Cart getCart(User user);
    public boolean checkOutCart(Cart cart);
    public boolean removeProduct(Long cartId, Long productId);
    public boolean addProduct(Long cartId, Long productId);
    public boolean cartExists(User user);
    public boolean saveCart(Cart cart);
    public Cart getCartChecked(User user);
    public boolean updateQuantity(Integer CartOrderId, Integer quantity);
    public List<Cart> getAllCheckedCarts();
}
