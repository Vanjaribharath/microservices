package com.capgemini.training.application.service;


import com.capgemini.training.application.port.in.CreateProductUseCase;
import com.capgemini.training.application.port.in.GetProductUseCase;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final LoadProductPort loadProductPort;

    public ProductService(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }
    private List<Product> products = new ArrayList<>();
    private Long idCounter =1L;

    @Override
    public Product create(String name, double price) {
        Product product = new Product(idCounter++, name, price);
        products.add(product);
        return product;
    }

    @Override
    public String getProduct() {
        return loadProductPort.loadProduct();
    }


}
