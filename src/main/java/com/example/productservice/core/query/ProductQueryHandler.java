package com.example.productservice.core.query;

import com.example.productservice.command.rest.ProductRestModel;
import com.example.productservice.core.ProductEntity;
import com.example.productservice.core.data.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {
    public final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @QueryHandler
    List<ProductRestModel> findProducts(FindProductsQuery query){
        List<ProductRestModel> productsRest = new ArrayList<>();
        List<ProductEntity> storedProducts = productRepository.findAll();
        for (ProductEntity productEntity : storedProducts){
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productsRest.add(productRestModel);
        }

        return productsRest;
    }
}
