package com.cms.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guardwhy
 * @date 2022/3/8 10:26
 */
@Getter
@Setter
public class CmsUserPrimaryEntity {
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
