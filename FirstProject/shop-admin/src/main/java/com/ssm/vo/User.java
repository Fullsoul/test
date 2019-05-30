package com.ssm.vo;

import lombok.*;

import java.util.Date;


@Data
public class User {

    public static void main(String[] args) {
        User user = new User();
    }
    private Long userId;

    private String userAccount;

    private String userName;

    private String password;

    private String headimageurl;

    private String mobileNumber;

    private String email;

    private Integer status;

    private Date lastLoginTime;

    private Date createTime;

    private Long createUserId;

    private Date modifyTime;

    private Long modifyUserId;
}
