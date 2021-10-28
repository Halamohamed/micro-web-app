package com.example.ecutb.microwebapp.repositories;

import com.example.ecutb.microwebapp.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {


    Optional<TourPackage> findByName(String s);
}
