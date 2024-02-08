package com.ninja.learn_es.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier(){
        Supplier<Query> supplier= ()->Query.of(q->q.matchAll(matchAllQuery()));
        return supplier;
    }

    public static MatchAllQuery matchAllQuery(){
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierWithField(String fieldValue){
        Supplier<Query> supplier= ()->Query.of(q->q.match(matchQueryWithField(fieldValue)));
        return supplier;
    }

    public static MatchQuery matchQueryWithField(String fieldValue){
        val matchQuery = new MatchQuery.Builder();
        return matchQuery.field("name").query(fieldValue).build();
    }


    public static Supplier<Query> supplierBool(String cropName, Integer qty){
        Supplier<Query> supplier= ()->Query.of(q->q.bool(boolQuery(cropName, qty)));
        return supplier;
    }
    public static BoolQuery boolQuery(String cropName, Integer qty){
        val boolQuery = new BoolQuery.Builder();
        return boolQuery.filter(termQuery((cropName))).must(matchQuery(qty)).build();
    }

    public static List<Query> termQuery(String cropName){
        final List<Query> terms = new ArrayList<>();
        val termQuery =new TermQuery.Builder();
        terms.add(Query.of(q->q.term(termQuery.field("name").value(cropName).build())));
        return terms;
    }
    public static List<Query> matchQuery(Integer qty){
            final List<Query> matches = new ArrayList<>();
            val matchQuery =new MatchQuery.Builder();
            matches.add(Query.of(q->q.match(matchQuery.field("revenue").query(qty).build())));
            return matches;
    }

    public static Supplier<Query> supplierExistsQuery(List<String> fieldNames) {
        return () -> Query.of(q -> q.bool(boolExistsQuery(fieldNames)));
    }

    public static BoolQuery boolExistsQuery(List<String> fieldNames) {
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();
        for (String fieldName : fieldNames) {
            boolQuery.should(existsQuery(fieldName));
        }
        return boolQuery.minimumShouldMatch(String.valueOf(1)).build();
    }

    public static List<Query> existsQuery(String fieldName) {
        List<Query> existsQueries = new ArrayList<>();
        val existQuery = new ExistsQuery.Builder();
        existsQueries.add(Query.of(q->q.exists(existQuery.field(fieldName).build())));
        return existsQueries;

    }



}
