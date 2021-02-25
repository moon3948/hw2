package com.example.homework2;

public class Beer {
//    private String image;
//    private String name;
//    private int date_from;
//    private int date_to;
//    private int abv;

    private String name;
    private String description;
    private String image_url;
//    private String first_brewed;
//    private String food_pairings;
//    private String brewsterTips;
//    private int abv;

    public Beer (String name, String description,
                 String image_url) {
        this.name = name;
//        this.abv = abv;
//        this.first_brewed = first_brewed;
        this.image_url = image_url;
        this.description = description;
//        this.food_pairings = food_pairings;
//        this.brewsterTips = brewsterTips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
