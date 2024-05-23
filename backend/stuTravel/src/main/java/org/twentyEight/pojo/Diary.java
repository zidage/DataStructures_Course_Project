package org.twentyEight.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Diary {
    private Integer id;
    private String title;
    private String content;
    private String cover_img;
    private String state;
    private Integer create_user;
    private LocalDateTime crate_time;
    private LocalDateTime update_time;
}
