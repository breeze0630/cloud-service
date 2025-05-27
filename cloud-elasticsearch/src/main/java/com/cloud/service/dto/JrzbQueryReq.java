package com.cloud.service.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class JrzbQueryReq  extends LocalPageRequest {

    @ApiModelProperty(value = "地区，数组格式")
    private List<String> regions;

    @ApiModelProperty(value = "项目标题")
    private String title;

    @ApiModelProperty(value = "部门名称(服务类别)")
    private List<String> serviceTypeList;
}
