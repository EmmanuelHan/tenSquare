package com.tensquare.spit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class Spit implements Serializable {

    @SuppressWarnings("AlibabaAvoidStartWithDollarAndUnderLineNaming")
    @Id
    private String _id;

    private String content;//

    private Date publishTime;//

    private String userId;//

    private String nickName;//

    private Integer visits;//

    private Integer thumbUp;//

    private Integer share;//

    private Integer comment;//

    private String state;//状态

    private String parentId;//父编号


}
