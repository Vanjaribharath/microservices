////package com.capgemini.training.middleware.application.service;
////
////import com.capgemini.training.middleware.domain.CatalogOverview;
////import io.micrometer.observation.annotation.Observed;
////import org.springframework.stereotype.Service;
////
////@Service
////public class CatalogService {
////
////    @Observed(
////            name = "catalog.fetch",
////            contextualName = "fetch-catalog"
////    )
////    public CatalogOverview getCatalogOverview() {
////        // business logic here
////        return fetchFromRepository();
////    }
////}
//package com.capgemini.training.middleware.application.service;
//
//import com.capgemini.training.middleware.domain.CatalogOverview;
//import com.capgemini.training.middleware.repository.CatalogRepository;
//import io.micrometer.observation.annotation.Observed;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CatalogService {
//
//    private final CatalogRepository catalogRepository;
//
//    /**
//     * ✅ Custom BUSINESS span (Day 21 requirement)
//     */
//    @Observed(
//            name = "catalog.fetch",
//            contextualName = "fetch-catalog"
//    )
//    public CatalogOverview getCatalogOverview() {
//        return catalogRepository.fetchOverview();
//    }
//}

package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.domain.CatalogOverview;
import com.capgemini.training.middleware.adapter.out.feign.repository.CatalogRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Observed(
            name = "catalog.fetch",
            contextualName = "fetch-catalog"
    )
    public CatalogOverview getCatalogOverview() {
        return catalogRepository.fetchOverview();
    }
}
