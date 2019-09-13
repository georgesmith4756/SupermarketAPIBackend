package com.qa.controllersTest;

import com.qa.controllers.ProductController;
import com.qa.models.Product;
import com.qa.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository repository;

    @Test
    public void testGetProduct(){
        Product product = new Product();
        product.setProductName("Chair");
        product.setCost(50);
        product.setStock(10);
        product.setCategory("Furniture");
        product.setDescription("Can be sat on");

        when(repository.findOne(0L)).thenReturn(product);

        assertEquals(productController.getProduct(0L).getProductName(),"Chair");
        assertEquals(productController.getProduct(0L).getStock(),10);

    }

    @Test
    public void testGetAllProducts(){
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductName("Chair");
        product.setCost(50);
        product.setStock(10);
        product.setCategory("Furniture");
        product.setDescription("Can be sat on");
        productList.add(product);

        when(repository.findAll()).thenReturn(productList);

        assertEquals(productController.listAllProducts().get(0).getProductName(),"Chair");

    }

    @Test
    public void testCreateProduct(){
        Product product = new Product();
        product.setProductName("Chair");
        product.setCost(50);
        product.setStock(10);
        product.setCategory("Furniture");
        product.setDescription("Can be sat on");

        when(repository.saveAndFlush(product)).thenReturn(product);

        assertEquals(productController.addProduct(product).getProductName(),"Chair");
        assertEquals(productController.addProduct(product).getCost(),50);


    }

    @Test
    public void deleteProduct(){
        Product product = new Product();
        product.setProductName("Chair");
        product.setCost(50);
        product.setStock(10);
        product.setCategory("Furniture");
        product.setDescription("Can be sat on");

        when(repository.findOne(0L)).thenReturn(product);

        assertEquals(productController.deleteProduct(0L).getProductName(),"Chair");


    }

    @Test
    public void updateProduct(){
        Product product1 = new Product();
        product1.setProductName("Big Chair");
        product1.setCost(100);
        product1.setDescription("A big chair");
        product1.setCategory("Furniture");
        Product product2 = new Product();
        product2.setProductName("Small Chair");
        product2.setCost(50);
        product2.setDescription("A small chair");
        product2.setCategory("House");

        when(repository.findOne(0L)).thenReturn(product1);
        when(repository.saveAndFlush(product1)).thenReturn(product1);

        assertEquals(productController.updateProduct(0L,product2).getProductName(),"Small Chair");
        assertEquals(productController.updateProduct(0L,product2).getDescription(),"A small chair");
        assertEquals(productController.updateProduct(0L,product2).getCategory(),"House");
        
    }
}
