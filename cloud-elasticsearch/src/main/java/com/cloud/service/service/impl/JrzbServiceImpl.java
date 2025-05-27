package com.cloud.service.service.impl;


import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.DateRangeQuery;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.cloud.service.bean.JrzbBean;
import com.cloud.service.dto.JrzbQueryReq;
import com.cloud.service.dto.JrzbVo;
import com.cloud.service.dto.LizardCountVo;
import com.cloud.service.mapper.JrzbRepository;
import com.cloud.service.service.JrzbService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JrzbServiceImpl implements JrzbService {

    @Autowired
    private JrzbRepository jrzbRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

//    @Autowired
//    private RedisCache redisCache;

//    @Autowired
//    private JrzbManage jrzbManage;

    /**
     * 根据ID查询
     *
     */
    @Override
    public JrzbVo getById(String id) {
        JrzbBean jrzbBean = jrzbRepository.findById(id).orElse(null);
        if(ObjectUtils.isEmpty(jrzbBean)){
            return null;
        }

        /*
         * 判断是否查询了详情
         */
        if(StringUtils.isEmpty(jrzbBean.getSearch_detail()) || "0".equals(jrzbBean.getSearch_detail())){
            // 需要从 网站查询详情 并且写入到es中
            searchDetailFromWeb(jrzbBean);
            jrzbBean = jrzbRepository.findById(id).orElse(null);
        }

        JrzbVo jrzbVo = JrzbVo.builder().build();
        assert jrzbBean != null;
        jrzbVo.fromJrzbBean(jrzbBean);
        if("jrzb".equals(jrzbVo.getDataSource()) && StringUtils.isBlank(jrzbVo.getViewUrl())){
            jrzbVo.setViewUrl("https://console.jrzb.cn/search-detail?id=" + jrzbVo.getDataId());
        }
        return jrzbVo;
    }

    private void searchDetailFromWeb(JrzbBean data) {
    }

    /**
     * 一些 关于查询的ES代码示例
     * @param jrzbQueryReq
     * @return
     */
    @Override
    public Page<JrzbVo> list(JrzbQueryReq jrzbQueryReq) {
        log.info("检索条件:{}", JSON.toJSONString(jrzbQueryReq));

        Pageable pageable = PageRequest.of(jrzbQueryReq.getPage() - 1, jrzbQueryReq.getSize(), Sort.by("timestamp").descending());

        BoolQuery.Builder queryBuilder = new BoolQuery.Builder();
        //改为查询近7天的，按照 publishTime 查询

//        queryBuilder.must(t -> t.term(TermQuery.of(q -> q.field("insert_date").value(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))));

        queryBuilder.must( t -> t.range(r -> r.date(DateRangeQuery.of( q -> q.field("publishTime")
                .gte(LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .lte(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))));

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(jrzbQueryReq.getTitle())){
            queryBuilder.must(m -> m.match(t -> t.field("title").query(jrzbQueryReq.getTitle())));
        }
        if(CollectionUtils.isNotEmpty(jrzbQueryReq.getRegions())){
            BoolQuery.Builder cityNameBuilder = new BoolQuery.Builder();

            jrzbQueryReq.getRegions().forEach(k-> {
                cityNameBuilder.should(m -> m.match(t -> t.field("cityName").query(k)));
            });
            cityNameBuilder.minimumShouldMatch("1");
            queryBuilder.must(cityNameBuilder.build()._toQuery());
        }

        if(CollectionUtils.isNotEmpty(jrzbQueryReq.getServiceTypeList())){
            BoolQuery.Builder serviceType = new BoolQuery.Builder();
            jrzbQueryReq.getServiceTypeList().forEach( k-> {
                serviceType.should(m -> m.match(t -> t.field("service_type.names").query(k)));
            });
            serviceType.minimumShouldMatch("1");

            queryBuilder.must(serviceType.build()._toQuery());
        }


        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(queryBuilder.build()._toQuery())
                .withPageable(pageable);

        Query query = builder.build();

        SearchHits<JrzbBean> searchHits = elasticsearchOperations.search(query,JrzbBean.class);

        List<JrzbVo> voList =  searchHits.getSearchHits().stream().map(jrzbBean -> {
            JrzbVo jrzbVo = JrzbVo.builder().build();
            jrzbVo.fromJrzbBean4List(jrzbBean.getContent());
            if("jrzb".equals(jrzbVo.getDataSource()) && StringUtils.isBlank(jrzbVo.getViewUrl())){
                jrzbVo.setViewUrl("https://console.jrzb.cn/search-detail?id=" + jrzbVo.getDataId());
            }
            return jrzbVo;
        }).collect(Collectors.toList());

        long total = searchHits.getTotalHits();
        Page<JrzbVo> result = new PageImpl<>(voList, pageable,total);
        return result;
    }



    @Override
    public LizardCountVo getTodayCount() {
        LocalDate today = LocalDate.now();
        long count = jrzbRepository.countByDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LizardCountVo countVo = LizardCountVo.builder().total((int) count).build();
        BoolQuery.Builder queryBuilder = new BoolQuery.Builder();

        queryBuilder.must(m -> m.term(t -> t.field("insert_date").value(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));

        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(queryBuilder.build()._toQuery())
                .withMaxResults(1)
                .withAggregation("service_type_aggs",  Aggregation.of(a -> a.terms(t -> t.field("service_type.names"))));
        Query query = builder.build();
        SearchHits<JrzbBean> searchHits = elasticsearchOperations.search(query,JrzbBean.class);
        ElasticsearchAggregation serviceTypeAggs = ((ElasticsearchAggregations) searchHits.getAggregations()).get("service_type_aggs");
        Aggregate aggregate = serviceTypeAggs.aggregation().getAggregate();
        if(!ObjectUtils.isEmpty(aggregate)) {
            ((ArrayList<StringTermsBucket>) (((StringTermsAggregate) aggregate._get()).buckets()._get())).forEach(bucket -> {
                if ("评估".equals(bucket.key()._get())) {
                    countVo.setEvaluate((int) bucket.docCount());
                }
                if ("测绘".equals(bucket.key()._get())) {
                    countVo.setSurvey((int) bucket.docCount());
                }
                if ("规划".equals(bucket.key()._get())) {
                    countVo.setPlan((int) bucket.docCount());
                }
            });
        }
        return countVo;
    }
}
