package com.cloud.service.service;


import com.cloud.service.dto.JrzbQueryReq;
import com.cloud.service.dto.JrzbVo;
import com.cloud.service.dto.LizardCountVo;
import org.springframework.data.domain.Page;

public interface JrzbService {

    JrzbVo getById(String id);

    Page<JrzbVo> list(JrzbQueryReq jrzbQueryReq);

    LizardCountVo getTodayCount();

}
