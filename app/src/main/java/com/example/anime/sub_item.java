package com.example.anime;

public class sub_item {
    private String mYoutubeID;
    private String mLength;
    private String mSub_image;
    public sub_item(String youtubeID, String Length,String sub_image){
        mYoutubeID = youtubeID;
        mLength = Length;
        mSub_image = sub_image;
    }
    public String getmYoutubeID(){
        return mYoutubeID;
    }
    public String getmLength(){
        return mLength;
    }
    public String getmSub_image(){
        return mSub_image;
    }

}
