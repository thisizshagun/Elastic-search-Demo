package com.ninja.learn_es.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ninja.learn_es.entity.Crop;
import com.ninja.learn_es.service.CropService;
import com.ninja.learn_es.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/apis")
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    @ResponseBody
    List<Crop> findAll(){
        List<Crop> crop0= new ArrayList<>();
        Iterable<Crop> cropAll=cropService.getCrops();
        for(Crop crop: cropAll){
            crop0.add(crop);
        }
//return cropService.getCrops();
        return crop0;
    }

    @PostMapping("/insert")
    public Crop insertCrop(@RequestBody Crop crop){
        return cropService.insertCrop(crop);

    }
    @DeleteMapping("/delete")
    public void deleteCrop(@RequestParam int id){
        cropService.deleteCrop(id);
    }
    @PutMapping ("/update")
    public Crop update(@RequestBody Crop crop,@RequestParam int id){
        return cropService.updateCrop(crop, id);

    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException{

        return elasticSearchService.matchAllServices().hits().hits().toString();
    }

    @GetMapping("/matchField/{fieldValue}")
    public List<Map<String, Object>> matchField(@PathVariable String fieldValue) throws IOException{

        SearchResponse<Map> searchResponse= elasticSearchService.matchWithField(fieldValue);

        List<Hit<Map>> listOfHits = searchResponse.hits().hits();
        List<Map<String, Object>> cropList= new ArrayList<>();

        for(Hit<Map> hit: listOfHits){
            cropList.add(hit.source());
        }

        return cropList;
    }

    @GetMapping("/boolQuery/{cropName}/{qty}")
    public List<Map> boolQuery(@PathVariable String cropName, @PathVariable Integer qty) throws IOException{

        SearchResponse<Map> searchResponse= elasticSearchService.boolSearch(cropName, qty);

        List<Hit<Map>> listOfHits = searchResponse.hits().hits();
        List<Map> cropList= new ArrayList<>();

        for(Hit<Map> hit: listOfHits){
            cropList.add(hit.source());
        }

        return cropList;
    }

    @GetMapping("/existsQuery")
    public List<Map> existsQuery() throws IOException {
        List<String> fieldNames = Arrays.asList("name", "revenue", "peat_id", "crop_code");
        SearchResponse<Map> searchResponse = elasticSearchService.existsSearch(fieldNames);

        List<Hit<Map>> listOfHits = searchResponse.hits().hits();
        List<Map> cropList = new ArrayList<>();

        for (Hit<Map> hit : listOfHits) {
            cropList.add(hit.source());
        }

        return cropList;
    }


}
