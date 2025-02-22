package org.twentyEight.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

// lombok
@Data
public class User {
    @NotNull
    private Integer id;
    private String username;
    @JsonIgnore // 忽略password，最终的JSON字符串中没有password那个属性
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,16}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String userPic; // 用户图像地址
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
