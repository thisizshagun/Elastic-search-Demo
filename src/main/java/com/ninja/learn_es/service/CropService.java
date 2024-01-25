package com.ninja.learn_es.service;

import com.ninja.learn_es.entity.Crop;
import com.ninja.learn_es.repo.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    @Autowired

    private CropRepo cropRepo;

    public Iterable<Crop> getCrops(){
        return cropRepo.findAll();
    }
    public Crop insertCrop(Crop crop){
        return cropRepo.save(crop);
    }
    public Crop updateCrop(Crop crop, int id){
        Crop crop1= cropRepo.findById(id).get();
        crop1.setRevenue(crop.getRevenue());
        return crop1;

    }
    public void deleteCrop(int id){
        cropRepo.deleteById(id);
    }



}
