package com.ninja.learn_es.controller;

import com.ninja.learn_es.entity.Crop;
import com.ninja.learn_es.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/apis")
public class CropController {

    @Autowired
    private CropService cropService;

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
}
