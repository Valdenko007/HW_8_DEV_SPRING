package ua.goit.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.dev.model.dao.Producer;
import ua.goit.dev.model.dao.Product;
import ua.goit.dev.service.ProducerService;
import ua.goit.dev.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProducerService producerService;
    @GetMapping("/{id}")
    public String findById(@PathVariable UUID id, Model model){
        Producer producer= producerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("product", producer.getProducts());
        model.addAttribute("pe",producer);
        return "products";
    }
    @GetMapping("/new/{id}")
    public String create(Model model,@PathVariable("id") UUID id) {
        model.addAttribute("product", new Product());
        model.addAttribute("id",id);
        return "addFromProduct";
    }
    @PostMapping(path = "/new/{id}")
    public String addOrUpdateProduct(@ModelAttribute("product") Product product,@PathVariable("id") UUID id) {
        Producer producer= producerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Product p=new Product();
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setProducer(producer);
         productService.createOrUpdate(p);
        return "redirect:/product/"+product.getId();
    }
    @GetMapping("remove/{id}")
    public String delete(@PathVariable UUID id){
         Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productService.deleteById(id);
        return "redirect:/product/"+product.getProducer().getId();
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") UUID id, Model model) {
         Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("product", product);
        return "updateProduct";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") UUID id, @ModelAttribute("product") Product product) {
        final Product products = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        products.setName(product.getName());
        products.setPrice(product.getPrice());
        productService.createOrUpdate(products);
        return "redirect:/product/"+products.getProducer().getId();
    }
}
