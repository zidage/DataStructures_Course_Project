package org.twentyEight.pojo;

import lombok.Data;

import java.time.LocalDateTime;

// lombok
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String userPic; // 用户图像地址
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
