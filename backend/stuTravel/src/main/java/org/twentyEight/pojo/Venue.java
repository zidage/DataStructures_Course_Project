package org.twentyEight.pojo;

import lombok.Data;

@Data
public class Venue {
    private Integer id;
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private Integer placeId; // 外键
    private Integer popularity;
    private Long osmid;
}
