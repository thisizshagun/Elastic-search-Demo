package com.ninja.learn_es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "crops")

public class Crop {
    private int id;
    private String name;

    private String description;

    private int revenue;

}
