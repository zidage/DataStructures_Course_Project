package org.twentyEight.pojo;

import lombok.Data;

@Data
public class Venue {
    private Integer id;
    private Integer place_belong;
    private Integer route_belong;
    private String type;
    private String name;
    private String osmid;
}
