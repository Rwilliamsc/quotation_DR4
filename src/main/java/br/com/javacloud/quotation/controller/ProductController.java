package br.com.javacloud.quotation.controller;

import br.com.javacloud.quotation.model.domain.Product;
import br.com.javacloud.quotation.model.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "get all products")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get all products") })
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get product By Id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get product By Id"),
            @ApiResponse(responseCode = "404", description = "product not found") })
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "create product")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "create product") })
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update product")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "update product"),
            @ApiResponse(responseCode = "404", description = "product not found") })
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            product.setId(id);
            Product savedProduct = productService.update(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "delete product"),
            @ApiResponse(responseCode = "404", description = "product not found") })
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
