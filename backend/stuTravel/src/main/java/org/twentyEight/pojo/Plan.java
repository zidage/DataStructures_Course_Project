package org.twentyEight.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Plan {
    private Integer id;
    private Integer createUser;
    private String title;
    private Long placeId;
    private String transport;
    private Integer requiredTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
