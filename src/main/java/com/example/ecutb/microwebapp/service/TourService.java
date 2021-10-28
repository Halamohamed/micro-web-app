package com.example.ecutb.microwebapp.service;

import com.example.ecutb.microwebapp.domain.Difficulty;
import com.example.ecutb.microwebapp.domain.Region;
import com.example.ecutb.microwebapp.domain.Tour;
import com.example.ecutb.microwebapp.domain.TourPackage;
import com.example.ecutb.microwebapp.repositories.TourPackageRepository;
import com.example.ecutb.microwebapp.repositories.TourRepository;



public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration, String bullets,
                           String keywords, String tourPackageName, Difficulty difficulty, Region region){
       TourPackage tourPackage= tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
               new RuntimeException("Tour package does not exist" + tourPackageName) );
        return tourRepository.save(new Tour(title,description,blurb,price,duration,bullets,keywords,tourPackage,difficulty,region));
    }

    public long total(){
        return tourRepository.count();
    }
}
