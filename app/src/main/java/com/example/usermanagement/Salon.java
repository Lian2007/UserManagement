package com.example.usermanagement;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Salon implements Parcelable
{

    private String name;
    private String address;
    private String phone;
    private String website;
    private String photo;

    public Salon() {
    }

    public Salon(String name, String address, String phone, String website) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.photo = photo;
    }

    protected Salon(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        website = in.readString();
        photo = in.readString();
    }

    public static final Creator<Salon> CREATOR = new Creator<Salon>() {
        @Override
        public Salon createFromParcel(Parcel in) {
            return new Salon(in);
        }

        @Override
        public Salon[] newArray(int size) {
            return new Salon[size];
        }
    };

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(website);
        parcel.writeString(photo);
    }
}



