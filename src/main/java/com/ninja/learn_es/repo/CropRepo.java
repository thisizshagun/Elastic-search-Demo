package com.ninja.learn_es.repo;

import com.ninja.learn_es.entity.Crop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CropRepo extends ElasticsearchRepository<Crop,Integer> {

}
