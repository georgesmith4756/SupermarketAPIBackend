package com.qa.controllers;

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

    @RequestMapping(value = "product/{id}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "product/{id}",method = RequestMethod.DELETE)
    public Product deleteProduct(@PathVariable Long id){
        Product existing = repository.findOne(id);
        repository.delete(existing);
        return existing;
    }

    @RequestMapping(value = "product/{id}",method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product existing = repository.findOne(id);
        existing.setSomething(product.getsomething());
        repository.saveAndFlush(existing);
        return existing;
    }


}
