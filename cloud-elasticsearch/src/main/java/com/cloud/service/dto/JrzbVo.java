package com.cloud.service.dto;


import com.cloud.service.bean.JrzbBean;
import com.cloud.service.bean.KeyWords;
import com.cloud.service.bean.ServiceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel(value = "招标信息对象")
@AllArgsConstructor
@NoArgsConstructor
public class JrzbVo {


        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "结果快照 招标详情")
        private String resultSnapshot;

        @ApiModelProperty(value = "项目名称")
        private String projectName;

        @ApiModelProperty(value = "项目标题")
        private String title;
        /**
         *
         */
        @ApiModelProperty(value = "项目预算")
        private String projectBudgetStr;

        @ApiModelProperty(value = "报名结束时间")
        private String overTimeSign;

        @ApiModelProperty(value = "中标结束时间")
        private String overTimeBid;

        @ApiModelProperty(value = "业主联系人")
        private String extContact;

        @ApiModelProperty(value = "业主联系人电话")
         private String extContactTell;

        @ApiModelProperty(value = "代理机构")
        private String extAgency;
        @ApiModelProperty(value = "代理联系人")
        private String extAgencyName;
        @ApiModelProperty(value = "代理联系人电话")
        private String extAgencyTell;

        @ApiModelProperty(value = "原文")
        private String viewUrl;
        @ApiModelProperty(value = "项目编号")

        private String projectNumber;

        @ApiModelProperty(value = "发布时间")
        private String publishTime;

        @ApiModelProperty(value = "数据来源")
        private String dataSource;

        @ApiModelProperty(value = "原网站的数据id")
        private String dataId;

        @ApiModelProperty(value = "爬取入库时间戳")
        private String timestamp;

        @ApiModelProperty(value = "入库时间")
        private String insertDate;

        @ApiModelProperty(value = "地区")
        private String cityName;

        /**
         * 业主名称
         */
        @ApiModelProperty(value = "业主名称")
        private String extTender;

        private ServiceType serviceType;

        private KeyWords keyWords;

        private String searchDetail;

        public void fromJrzbBean(JrzbBean jrzbBean) {
            this.id = jrzbBean.getId();
            this.projectName = jrzbBean.getProjectName();
            this.title = jrzbBean.getTitle();
            this.projectBudgetStr = jrzbBean.getProjectBudgetStr();
            this.overTimeSign = jrzbBean.getOverTimeSign();
            this.overTimeBid = jrzbBean.getOverTimeBid();
            this.extContact = jrzbBean.getExt_contact();
            this.extContactTell = jrzbBean.getExt_contactTell();
            this.extAgency = jrzbBean.getExt_agency();
            this.extAgencyName = jrzbBean.getExt_agencyName();
            this.extAgencyTell = jrzbBean.getExt_agencyTell();
            this.viewUrl = jrzbBean.getViewUrl();
            this.projectNumber = jrzbBean.getProjectNumber();
            this.publishTime = jrzbBean.getPublishTime();
            this.dataSource = jrzbBean.getData_source();
            this.dataId = jrzbBean.getData_id();
            this.timestamp = jrzbBean.getTimestamp();
            this.insertDate = jrzbBean.getInsert_date();
            this.resultSnapshot = jrzbBean.getResultSnapshoot();
            this.cityName = jrzbBean.getCityName();
            this.extTender = jrzbBean.getExt_tender();
            this.serviceType = jrzbBean.getService_type();
            this.keyWords = jrzbBean.getKey_words();
            this.searchDetail = jrzbBean.getSearch_detail();
        }

    public void fromJrzbBean4List(JrzbBean jrzbBean) {
        this.id = jrzbBean.getId();
        this.projectName = jrzbBean.getProjectName();
        this.title = jrzbBean.getTitle();
        this.projectBudgetStr = jrzbBean.getProjectBudgetStr();
        this.overTimeSign = jrzbBean.getOverTimeSign();
        this.overTimeBid = jrzbBean.getOverTimeBid();
        this.extContact = jrzbBean.getExt_contact();
        this.extContactTell = jrzbBean.getExt_contactTell();
        this.extAgency = jrzbBean.getExt_agency();
        this.extAgencyName = jrzbBean.getExt_agencyName();
        this.extAgencyTell = jrzbBean.getExt_agencyTell();
        this.viewUrl = jrzbBean.getViewUrl();
        this.projectNumber = jrzbBean.getProjectNumber();
        this.publishTime = jrzbBean.getPublishTime();
        this.dataSource = jrzbBean.getData_source();
        this.dataId = jrzbBean.getData_id();
        this.timestamp = jrzbBean.getTimestamp();
        this.insertDate = jrzbBean.getInsert_date();
        this.cityName = jrzbBean.getCityName();
        this.extTender = jrzbBean.getExt_tender();
        this.serviceType = jrzbBean.getService_type();
        this.keyWords = jrzbBean.getKey_words();
        this.searchDetail = jrzbBean.getSearch_detail();
    }

}
