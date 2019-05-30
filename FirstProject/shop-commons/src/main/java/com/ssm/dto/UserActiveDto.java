package com.ssm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserActiveDto {
    private Long userId;

    private String userAccount;

    private String userName;

    private String headimageurl;

}
