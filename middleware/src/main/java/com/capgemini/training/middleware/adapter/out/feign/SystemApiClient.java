//package com.capgemini.training.middleware.adapter.out.feign;
//
////package com.capgemini.training.middleware.adapter.out.feign;
////package com.capgemini.training.middleware.adapter.out.feign;
////
////import com.capgemini.training.middleware.adapter.out.feign.dto.*;
////import org.springframework.cloud.openfeign.FeignClient;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
//import java.util.UUID;
//
////@FeignClient(
////        name = "system-api",
////        url = "${system-api.url}"
////)
////public interface SystemApiClient {
////
////    @GetMapping("/api/v1/products/{id}")
////    ProductDTO getProductById(@PathVariable UUID id);
////
////    @GetMapping("/api/v1/categories/{id}")
////    CategoryDTO getCategoryById(@PathVariable Long id);
////
////    @GetMapping("/api/v1/suppliers/{id}")
////    SupplierDTO getSupplierById(@PathVariable Long id);
////}
//
//
////package com.capgemini.training.middleware.adapter.out.feign;
//
//import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
//import com.capgemini.training.middleware.adapter.out.feign.dto.CategoryDTO;
//import com.capgemini.training.middleware.adapter.out.feign.dto.SupplierDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.UUID;
//
//@FeignClient(
//        name = "system-api",
//        url = "http://localhost:8082"
//)
//public interface SystemApiClient {
//
//    // ✅ Products
//    @GetMapping("/api/v1/products/{id}")
//    ProductDTO getProductById(@PathVariable UUID id);
//
//    @GetMapping("/api/v1/products")
//    List<ProductDTO> getProducts(
//            @RequestParam int page,
//            @RequestParam int size
//    );
//
//    @GetMapping("/api/v1/products/featured")
//    List<ProductDTO> getFeaturedProducts();
//
//    // ✅ Categories
//    @GetMapping("/api/v1/categories")
//    List<CategoryDTO> getCategories();
//
//    @GetMapping("/api/v1/categories/{id}")
//    CategoryDTO getCategoryById(@PathVariable Long id);
//
//    // ✅ Suppliers
//    @GetMapping("/api/v1/suppliers/{id}")
//    SupplierDTO getSupplierById(@PathVariable Long id);
//}

package com.capgemini.training.middleware.adapter.out.feign;

import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
import com.capgemini.training.middleware.adapter.out.feign.dto.CategoryDTO;
import com.capgemini.training.middleware.adapter.out.feign.dto.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "system-api",
        url = "http://localhost:8082"
)
public interface SystemApiClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable UUID id);

    @GetMapping("/api/v1/products")
    List<ProductDTO> getProducts(
            @RequestParam int page,
            @RequestParam int size
    );

    @GetMapping("/api/v1/products/featured")
    List<ProductDTO> getFeaturedProducts();

    @GetMapping("/api/v1/categories")
    List<CategoryDTO> getCategories();

    @GetMapping("/api/v1/categories/{id}")
    CategoryDTO getCategoryById(@PathVariable Long id);

    @GetMapping("/api/v1/suppliers/{id}")
    SupplierDTO getSupplierById(@PathVariable Long id);
}