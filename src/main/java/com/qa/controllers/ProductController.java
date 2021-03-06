package com.qa.controllers;

import com.qa.models.Product;
import com.qa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public List<Product> listAllProducts(){
        return repository.findAll();
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        return repository.saveAndFlush(product);
    }

    @RequestMapping(value = "products/{id}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "products/{id}",method = RequestMethod.DELETE)
    public Product deleteProduct(@PathVariable Long id){
        Product existing = repository.findOne(id);
        repository.delete(existing);
        return existing;
    }

    @RequestMapping(value = "products/{id}",method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product existing = repository.findOne(id);
        existing.setProductName(product.getProductName());
        existing.setCost(product.getCost());
        existing.setStock(product.getStock());
        existing.setDescription(product.getDescription());
        existing.setCategory(product.getCategory());
        repository.saveAndFlush(existing);
        return existing;
    }


}
