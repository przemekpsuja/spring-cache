package com.example.caching.controler;

import com.example.caching.domain.Product;
import com.example.caching.model.Products;
import com.example.caching.services.ProductCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductCrudService productCrudService;

    public ProductController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @GetMapping
    public Products getAll(){
        return new Products(productCrudService.getAllProducts());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable final Long id){
        return  productCrudService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED )
    public Product createProduct(@RequestBody final Product product){
        return productCrudService.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable final Long id, @RequestBody final Product product){
        productCrudService.update(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable final Long id){
        productCrudService.deleteProduct(id);
    }

}
