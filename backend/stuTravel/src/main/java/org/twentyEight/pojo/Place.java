package org.twentyEight.pojo;

import lombok.Data;

@Data
public class Place {
    private Long id;
    private String name;
    private Integer popularity;
    private Double rating;
    private String data_path;
    private String address;
}
