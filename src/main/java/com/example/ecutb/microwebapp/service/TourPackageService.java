package com.example.ecutb.microwebapp.service;

import com.example.ecutb.microwebapp.domain.TourPackage;
import com.example.ecutb.microwebapp.repositories.TourPackageRepository;

public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name){
        return tourPackageRepository.findById(code)
                .orElse(tourPackageRepository.save(new TourPackage(code,name)));
    }
    public Iterable<TourPackage> lookup(){
        return tourPackageRepository.findAll();
    }
    public long total(){
        return tourPackageRepository.count();
    }
}
