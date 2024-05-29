////package com.ecommerce.finalexam.controller;
////
//////public class CheckoutController {
//////}
////
////import com.ecommerce.finalexam.cart.Cart;
////import com.ecommerce.finalexam.services.CheckoutServices;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////// CheckoutController.java
////@RestController
////@RequestMapping("/checkout")
////public class CheckoutController {
////
////    @Autowired
////    private CheckoutServices checkoutService;
////
////    @GetMapping("/all")
////    public List<Cart> getAllCheckouts() {
////        return checkoutService.getAllCheckouts();
////    }
////
////    @PostMapping("/approve/{id}")
////    public void approveCheckout(@PathVariable Long id) {
////        checkoutService.approveCheckout(id);
////    }
////
////    @PostMapping("/deny/{id}")
////    public void denyCheckout(@PathVariable Long id) {
////        checkoutService.denyCheckout(id);
////    }
////
////    @PostMapping("/delete/{id}")
////    public void deleteCheckout(@PathVariable Long id) {
////        checkoutService.deleteCheckout(id);
////    }
////}
//
//package com.ecommerce.finalexam.controller;
//
//import com.ecommerce.finalexam.cart.Cart;
//
//import com.ecommerce.finalexam.services.CheckoutServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller // Note the change to @Controller
//@RequestMapping("/checkout")
//public class CheckoutController {
//
//    @Autowired
//    private CheckoutServices checkoutService;
//
//    @GetMapping("/all")
//    public String getAllCheckouts(Model model) { // Updated to return a view name
//        List<Cart> carts = checkoutService.getAllCheckouts();
//        model.addAttribute("carts", carts);
//        return "redirect:/approvemessage"; // Assuming you have a Thymeleaf template named "all.html" under "src/main/resources/templates/checkout/"
//
//    }
//
//    @PostMapping("/approve/{id}")
//    public String approveCheckout(@PathVariable Long id) {
//        checkoutService.approveCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the page showing all checkouts
//
//
//    }
//
//    @PostMapping("/deny/{id}")
//    public String denyCheckout(@PathVariable Long id) {
//        checkoutService.denyCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the page showing all checkouts
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteCheckout(@PathVariable Long id) {
//        checkoutService.deleteCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the page showing all checkouts
//    }
//}

//package com.ecommerce.finalexam.controller;
//
//import com.ecommerce.finalexam.cart.Cart;
//import com.ecommerce.finalexam.services.CheckoutServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/checkout")
//public class CheckoutController {
//
//    @Autowired
//    private CheckoutServices checkoutService;
//
//    @GetMapping("/all")
//    public String getAllCheckouts(Model model) {
//        List<Cart> carts = checkoutService.getAllCheckouts();
//        model.addAttribute("carts", carts);
//        return "redirect:/approvemessage"; // Redirect to the approvemessage page
//    }
//
//    @PostMapping("/approve/{id}")
//    public String approveCheckout(@PathVariable Long id) {
//        checkoutService.approveCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the approvemessage page
//    }
//
//    @PostMapping("/deny/{id}")
//    public String denyCheckout(@PathVariable Long id) {
//        checkoutService.denyCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the approvemessage page
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteCheckout(@PathVariable Long id) {
//        checkoutService.deleteCheckout(id);
//        return "redirect:/approvemessage"; // Redirect to the approvemessage page
//    }
//}
package com.ecommerce.finalexam.controller;

import com.ecommerce.finalexam.cart.Cart;
import com.ecommerce.finalexam.services.CheckoutServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutServices checkoutService;

    @GetMapping("/all")
    public String getAllCheckouts(Model model) {
        List<Cart> carts = checkoutService.getAllCheckouts();
        model.addAttribute("carts", carts);
        return "checkout/all"; // Assuming you have a Thymeleaf template named "all.html" under "src/main/resources/templates/checkout/"
    }

    @PostMapping("/approve/{id}")
    public String approveCheckout(@PathVariable Long id, Model model) {
        checkoutService.approveCheckout(id);
        model.addAttribute("message", "Thank you for Ordering with us. It's on the way to your address.");
        return "redirect:/checkout";
    }

    @PostMapping("/deny/{id}")
    public String denyCheckout(@PathVariable Long id, Model model) {
        checkoutService.denyCheckout(id);
        model.addAttribute("message", "Your order has been denied.We regret to inform you that your order has been denied. Please contact customer support for more information.");
        return "redirect:/checkout";
    }

    @PostMapping("/delete/{id}")
    public String deleteCheckout(@PathVariable Long id, Model model) {
        checkoutService.deleteCheckout(id);
        model.addAttribute("message", "The Product You Order is not longer in The stock. Please try to Choose another product which is in our stock.Your money was refunded to your visa card Thank you for shopping with us!\"");
        return "redirect:/checkout";
    }

    @GetMapping("/approvemessage")
    public String approveMessage(Model model) {
        return "checkout";
    }
}

