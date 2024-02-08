package com.ninja.learn_es.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ninja.learn_es.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier= ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s->s.query(supplier.get()), Map.class);
        System.out.println(supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Map> matchWithField(String fieldValue) throws IOException {
            Supplier<Query> supplier= ElasticSearchUtil.supplierWithField(fieldValue);
            SearchResponse<Map> searchResponse = elasticsearchClient.search(s->s.index("crops").query(supplier.get()), Map.class);
            System.out.println(supplier.get().toString());
            return searchResponse;
        }
    public SearchResponse<Map> boolSearch(String cropName, Integer qty) throws IOException {
                Supplier<Query> supplier= ElasticSearchUtil.supplierBool(cropName, qty);
                SearchResponse<Map> searchResponse = elasticsearchClient.search(s->s.index("crops").query(supplier.get()), Map.class);
                System.out.println(supplier.get().toString());
                return searchResponse;
    }

    public SearchResponse<Map> existsSearch(List<String> fieldNames) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierExistsQuery(fieldNames);
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.index("crops").query(supplier.get()), Map.class);
        System.out.println(supplier.get().toString());
        return searchResponse;
    }





}
