package com.example.anime;

public class item1 {
    private String Image_resource;
    private String title;
    public item1(String image, String title_){
        Image_resource = image;
        title = title_;
    }
    public String getImage_resource(){
        return Image_resource;
    }
    public String getTitle(){
        return title;
    }

}
