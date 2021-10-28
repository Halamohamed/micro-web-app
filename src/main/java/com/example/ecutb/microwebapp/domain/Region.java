package com.example.ecutb.microwebapp.domain;

public enum Region {
    Central_Coast("Central Coast"), Karlshamn("Karlshamn");

    private String label;

    private Region(String label) { this.label = label;}

    public static Region findByLabel(String byLabel){
        for (Region r : Region.values()){
            if(r.label.equalsIgnoreCase(byLabel))
                return r;
        }
        return null;
    }
}
