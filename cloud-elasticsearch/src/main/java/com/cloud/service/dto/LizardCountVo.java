package com.cloud.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LizardCountVo {

    private int total ;

    private int evaluate;

    private int survey;

    private int plan;


}
