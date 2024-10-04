package com.loonds.places.config;

import com.loonds.places.entity.Vendor;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class HibernateSearchIndexBuilder  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private final EntityManager entityManager;

    public HibernateSearchIndexBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    @Async
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SearchSession searchSession = Search.session(entityManager);

        MassIndexer indexer = searchSession.massIndexer(Vendor.class).threadsToLoadObjects(7);
        try {
            indexer.startAndWait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//public class HibernateSearchIndexBuilder implements ApplicationListener<ApplicationReadyEvent> {
//
//    private final EntityManager entityManager;
//
//    public HibernateSearchIndexBuilder(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional(timeout = 180)
//    public void onApplicationEvent(ApplicationReadyEvent event) {
////        if (ThreadLocalRandom.current().nextInt(10) == 3) {
//            indexAll();
////        } else {
//            log.info("Not running Hibernate Search indexing.");
////        }
//    }
//
//    @Transactional(timeout = 180)
//    public void indexAll() {
//        SearchSession searchSession = Search.session(entityManager);
//        MassIndexer indexer = searchSession.massIndexer(Vendor.class)
//                .idFetchSize(Integer.MIN_VALUE)
//                .batchSizeToLoadObjects(100)
//                .threadsToLoadObjects(4);
//        try {
//            indexer.startAndWait();
//            log.info("Completed mass indexing for all the entities in HS.");
//        } catch (InterruptedException e) {
//            log.warn("Failed to load data from database");
//            Thread.currentThread().interrupt();
//        }
//    }
//}
