package com.capgemini.training.adapter.out.persistence;

import com.capgemini.training.application.port.out.LoadProductPort;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements LoadProductPort {
    @Override
    public String loadProduct() {
        return "Product from DB(fake)";
    }
}
