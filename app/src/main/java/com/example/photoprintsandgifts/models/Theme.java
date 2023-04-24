package com.example.photoprintsandgifts.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Theme implements Parcelable {
    String name, imagePath;



     public Theme(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

public Theme(){}

    protected Theme(Parcel in) {
        name = in.readString();
        imagePath = in.readString();
    }

    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  public String getImagePath() {
        return imagePath;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imagePath);
    }
}
