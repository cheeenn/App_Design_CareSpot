package model;

import android.widget.ImageView;

public class Listitem {
    public  String  name;
    public  String  description;
    private String  Rating;
    //private ImageView icon;

    public Listitem(String name, String description,String Rating) {
        this.name = name;
        this.description = description;
        this.Rating = Rating;
        //this.icon = icon;

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

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
//
//    public ImageView getIcon() {
//        return icon;
//    }
//
//    public void setIcon(ImageView icon) {
//        this.icon = icon;
//    }


}
