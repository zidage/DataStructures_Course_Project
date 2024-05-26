package org.twentyEight.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Diary {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private Integer createUser;
    private Integer popularity;
    private LocalDateTime crateTime;
    private LocalDateTime updateTime;
}