package org.twentyEight.pojo;

import lombok.Data;

@Data
public class Plan {
    private Integer id;
    private Integer createUser;
    private String title;
    private Integer placeId;
    private String transport;
    private Integer requiredTime;
}
