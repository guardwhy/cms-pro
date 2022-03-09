package com.cms.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guardwhy
 * @date 2022/3/9 10:31
 * 将po转换成dto
 */
@Getter
@Setter
public class CmsUserPrimaryDto {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
    private Boolean status;
    private Boolean deleted;
}
