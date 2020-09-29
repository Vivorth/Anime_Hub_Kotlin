package com.example.anime;

public class item {
    private String Image_resource;
    private String title;
    public item(String image, String title_){
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
