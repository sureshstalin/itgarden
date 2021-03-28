
package com.itgarden.controller;

import com.itgarden.entity.CustomerReview;
import com.itgarden.entity.Product;
import com.itgarden.services.CustomerService;
import com.itgarden.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    CustomerService customerService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
    }

    @GetMapping("/customers/reviews")
    public ResponseEntity<List<CustomerReview>> getCustomerReviews() {

        List<CustomerReview> reviews = customerService.getAllReviews();
        ResponseEntity<List<CustomerReview>> responseEntity = new ResponseEntity<>(reviews, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/customers/likes")
    public ResponseEntity<Long> getCustomerLikes() {
        Long likesCount = customerService.getLikesCount();
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(likesCount, HttpStatus.OK);
        return responseEntity;
    }

//    Hypermedia as the Engine of Application State (HATEOAS)
    @GetMapping("/{productId}")
    public ResponseEntity<EntityModel<Product>> getProduct(@PathVariable Long productId) throws Exception {
        Product product = productService.getProductById(productId);
        EntityModel<Product> resource = EntityModel.of(product);

        try {
            if (product == null) {
                throw new Exception("Product is not found");
            }
//            http://localhost:8080//api/products/customers/reviews
            WebMvcLinkBuilder reviewsLink = linkTo(methodOn(this.getClass()).getCustomerReviews()); //
            resource.add(reviewsLink.withRel("Reviews")); // Title
            WebMvcLinkBuilder likesLink = linkTo(methodOn(this.getClass()).getCustomerLikes());
//            http://localhost:8080//api/products/customers/likes
            resource.add(likesLink.withRel("Likes"));
            return new ResponseEntity<EntityModel<Product>>(resource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<EntityModel<Product>>(resource, HttpStatus.NOT_FOUND);
        }
    }
}
