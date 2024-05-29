package com.ecommerce.finalexam.product;

//import ch.qos.logback.core.model.Model;
import com.ecommerce.finalexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/product-new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct";
    }


    @PostMapping("/admin/add")
    public String addProduct(@ModelAttribute @RequestBody Product product, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        product.setUser(userService.findByEmail(userDetails.getUsername()));
        productService.save(product);
        return "redirect:/admin/all";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/home";
    }
    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
    @RequestMapping("/admin/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
       return "products";
    }
    @GetMapping("/product/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "updateproduct";
    }
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, Model model) {
        Product existingProduct = productService.findById(id);
        if (existingProduct!= null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setImage(product.getImage());
            productService.save(existingProduct);
        }
        return "redirect:/admin/all";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="name", required=false) String name,
                         Model model) {
        List<Product> searchResults = productService.searchProducts(name);
        model.addAttribute("results", searchResults);
        return "searchResults";
    }

    private List<String> performSearch(String name, String category) {
        List<String> results = new ArrayList<>();

        // Fetch all products from the database
        List<Product> allProducts = productService.getAllProducts();

        // Filter products based on the provided name and category
        for (Product product : allProducts) {
            boolean nameMatch = name == null || product.getName().toLowerCase().contains(name.toLowerCase());
            boolean categoryMatch = category == null || product.getCategory().toLowerCase().contains(category.toLowerCase());

            if (nameMatch && categoryMatch) {
                results.add(product.getName() + " - " + product.getCategory());
            }
        }

        return results;
    }
}
