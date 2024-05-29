package com.ecommerce.finalexam.cart;

import com.ecommerce.finalexam.product.Product;
import com.ecommerce.finalexam.product.ProductService;
import com.ecommerce.finalexam.services.UserService;
import com.ecommerce.finalexam.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping("/user/home")
    public String getUserProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "userHome";
    }
    @GetMapping("/all-products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }


    @PostMapping("/user/add-product")
    public String addProduct(Product product, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(userDetails.getUsername());
        if (cartService.cartExists(user)) {
            Cart cart = cartService.getCartChecked(user);
            if (!cartService.addProduct(cart.getId(), product.getId())) {
                redirectAttributes.addFlashAttribute("message", "You have already added this product");
                return "redirect:/user/home";
            }
        } else {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProducts(List.of(product));
            cartService.saveCart(cart);
        }
        redirectAttributes.addFlashAttribute("message", "You have successfully added this product");
        return "redirect:/user/home";

    }

    @GetMapping("/user/cart")
    public String getCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        Cart cart = cartService.getCartChecked(user);
        List<CartOrder> products;
        if (cart == null) {
            products = new ArrayList<>();
        } else {
            products = cart.getCartOrders();
        }
        model.addAttribute("carts", products);

        return "carts";
    }

    @GetMapping("/user/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByEmail(userDetails.getUsername());
            Cart cart = cartService.getCartChecked(user);


            if (cart.getProducts().isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Your cart is empty. Please add some products before checking out.");
                return "redirect:/user/cart";
            }

            cart.setChecked(true);
            cartService.checkOutCart(cart);


            redirectAttributes.addFlashAttribute("message", "Checkout successful!");
            return "redirect:/user/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An error occurred during checkout. Please try again.");
            return "redirect:/user/cart";
        }
    }

    @PostMapping("/user/update-quantity")
    public String updateQuantity(Integer cartOrderId, int quantity, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(userDetails.getUsername());
        Cart cart = cartService.getCartChecked(user);
        if (cart == null) {
            redirectAttributes.addFlashAttribute("message", "Your cart is empty");
            return "redirect:/user/cart";
        }

        boolean updated = cartService.updateQuantity(cartOrderId, quantity);
        if (updated) {
            redirectAttributes.addFlashAttribute("message", "Quantity updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to update quantity");
        }

        return "redirect:/user/cart";
    }
    @GetMapping("/checkout")
    public String getCheckouts(Model model){
        model.addAttribute("carts", cartService.getAllCheckedCarts());
        return "CheckoutManagement";
    }

}
