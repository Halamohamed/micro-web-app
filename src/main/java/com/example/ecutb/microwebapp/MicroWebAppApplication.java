package com.example.ecutb.microwebapp;

import com.example.ecutb.microwebapp.domain.Difficulty;
import com.example.ecutb.microwebapp.domain.Region;
import com.example.ecutb.microwebapp.service.TourPackageService;
import com.example.ecutb.microwebapp.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


import static javax.swing.text.html.parser.DTDConstants.ANY;
import static org.aspectj.weaver.ResolvedTypeMunger.Field;

@SpringBootApplication
public class MicroWebAppApplication implements CommandLineRunner {


    @Value("${ec.importfile}")
    private String importFile;
    private TourService tourService;

    private TourPackageService tourPackageService;

    public static void main(String[] args) {
        SpringApplication.run(MicroWebAppApplication.class, args);
    }

    private void loadToursAtStartup() throws IOException {
        createTourPackage();
        long numOfPackages = tourPackageService.total();

        createTours("ExploreCalifornia.json");
        long numOfTours = tourService.total();
    }

    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
                tourService.createTour(importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }

    private void createTourPackage() {
        tourPackageService.createTourPackage("BC","Backpack");
        tourPackageService.createTourPackage("BC","Backpack");
        tourPackageService.createTourPackage("BC","Backpack");
        tourPackageService.createTourPackage("BC","Backpack");
        tourPackageService.createTourPackage("BC","Backpack");
    }

    @Override
    public void run(String... args) throws Exception {

        createTourPackage();
    }
    private static class TourFromFile{
        private String packageType, title, description, blurb, bullets, keywords, length;
        Integer price;
        Difficulty difficulty;
        Region region;

        static List<TourFromFile> read(String fileToImport) throws  IOException {
            return new ObjectMapper().setVisibility(Field, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    });
        }
        protected TourFromFile(){}
        String getPackageType() {return packageType;}
        String getTitle() {return title;}
        String getDescription() {return description;}
        String getBlurb() {return blurb;}
        Integer getPrice() {return price;}
        String getKeywords() {return keywords;}
        Difficulty getDifficulty() {return difficulty;}
        Region getRegion() {return region;}
        String getLength() {return length;}
        String getBullets() {return bullets;}


    }
}
