package com.cloud.service.mapper;

import com.cloud.service.bean.JrzbBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;

public interface JrzbRepository extends ElasticsearchRepository<JrzbBean,String> {


    Page<JrzbBean> findByTitle(String title, Pageable pageable);

    Page<JrzbBean> findByCityNameIn(Collection<String> cityNames, Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"term\": {\"insert_date\": \"?0\"}}]}}")
    @Query(value = " {" +
            "    \"bool\": {" +
            "      \"filter\": [{\"term\": {\"insert_date\": \"?0\"}}]" +
            "    }" +
            "  }",count = true)
    long countByDate(String date);

    void removeById(String id);
}
