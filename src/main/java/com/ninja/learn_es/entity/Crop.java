package com.ninja.learn_es.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "crops")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crop {
    private int id;
    private String name;

    private String description;

    private int revenue;

}
