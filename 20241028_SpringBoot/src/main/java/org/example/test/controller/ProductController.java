package org.example.test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.request.ProductRequest;
import org.example.test.entity.ProductEntity;
import org.example.test.service.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.test.uitl.CategoryConstants.CATEGORIES;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/list")
    public String list(Model model) {
        List<ProductEntity> list = productService.findAll();
        model.addAttribute("products", list);
        return "products/index";
    }

    @GetMapping("/add")
    public String showCreatePage(Model model) {
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productDto", productRequest);
        model.addAttribute("CATEGORIES", CATEGORIES);
        return "products/add";
    }

    @PostMapping("/add")
    public String createProduct(
            @Valid @ModelAttribute("productDto") ProductRequest productRequest,
            BindingResult result
    ) {
        if (productRequest.getImageFileName().isEmpty()) {
            result.addError(new FieldError("productDto", "image", "Product image is required"));
        }

        if (result.hasErrors())
            return "products/add";

        productService.save(productRequest);

        return "redirect:/products/list";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        ProductEntity product;
        try {
            product = productService.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Invalid product Id:" + id)
            );

        } catch (IllegalArgumentException e) {
            return "redirect:/products/list";
        }
        model.addAttribute("CATEGORIES", CATEGORIES);
        model.addAttribute("product", product);
        model.addAttribute("productDto", productService.toProductRequest(product));

        return "products/edit";
    }

    @PostMapping("/edit")
    public String editProduct(
            Model model, @RequestParam int id, @Valid @ModelAttribute("productDto") ProductRequest productRequest,
            BindingResult result
    ) {
//        ProductEntity product;
//        try {
//            product = productService.findById(id).orElseThrow(
//                    () -> new IllegalArgumentException("Invalid product Id:" + id)
//            );
//
//        } catch (IllegalArgumentException e) {
//            return "/products/edit";
//        }
//        model.addAttribute("product", product);

        if (result.hasErrors())
            return "products/edit";

        productService.update(id, productRequest);

        return "redirect:/products/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        productService.delete(id);
        return "redirect:/products/list";
    }
}
