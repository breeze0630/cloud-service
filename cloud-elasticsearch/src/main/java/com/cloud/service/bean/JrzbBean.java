package com.cloud.service.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "index_lizard_jizb_2025")
public class JrzbBean {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String ResultSnapshoot;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String projectName;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String cityName;

    /**
     * 业主名称
     */
    @Field(type = FieldType.Keyword)
    private String ext_tender;

    /**
     *
     */
    @Field(type = FieldType.Keyword)
    private String projectBudgetStr;

    @Field(type = FieldType.Keyword)
    private String overTimeSign;

    @Field(type = FieldType.Keyword)
    private String overTimeBid;

    @Field(type = FieldType.Keyword)
    private String ext_contact;

    @Field(type = FieldType.Keyword)
    private String ext_contactTell;

    @Field(type = FieldType.Keyword)
    private String ext_agency;

    @Field(type = FieldType.Keyword)
    private String ext_agencyName;

    @Field(type = FieldType.Keyword)
    private String ext_agencyTell;

    @Field(type = FieldType.Keyword)
    private String viewUrl;

    @Field(type = FieldType.Keyword)
    private String projectNumber;

    @Field(type = FieldType.Keyword)
    private String publishTime;

    @Field(type = FieldType.Keyword)
    private String data_source;

    @Field(type = FieldType.Keyword)
    private String data_id;

    @Field(type = FieldType.Keyword)
    private String timestamp;

    @Field(type = FieldType.Keyword)
    private String insert_date;

    @Field(type = FieldType.Nested)
    private ServiceType service_type;

    // 0 未查询详情 1 已查询详情
    @Field(type = FieldType.Keyword)
    private String search_detail;

    @Field(type = FieldType.Nested)
    private KeyWords key_words;

}
