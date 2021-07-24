package com.example.albionrandomequipment;

import android.graphics.drawable.Drawable;

public class Equipment {
    String name;
    int material; //where 1 is cloth, 2 is leather, 3 is plate
    int is_artifact;
    int picture;
    boolean is_1handed;
    int type_of_weapon; //where 1 is mage, 2 is hunter, 3 is warrior




    public Equipment(String name, int is_artifact, boolean is_1handed, int type_of_weapon, int picture) {
        this.name = name;
        this.is_artifact = is_artifact;
        this.is_1handed = is_1handed;
        this.type_of_weapon = type_of_weapon;
        this.picture = picture;
    }


    public Equipment(String name, int is_artifact, int material, int picture) {
        this.name = name;
        this.is_artifact = is_artifact;
        this.material = material;
        this.picture = picture;
    }


    public Equipment(String name, int is_artifact, int material, int picture, boolean is_1handed) {
        this.name = name;
        this.is_artifact = is_artifact;
        this.material = material;
        this.picture = picture;
        this.is_1handed = is_1handed;
    }

    public Equipment(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public Equipment(String name, int is_artifact, int picture) {
        this.name=name;
        this.is_artifact=is_artifact;
        this.picture=picture;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIs_artifact(int is_artifact) {
        this.is_artifact = is_artifact;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public void setPicture(int picture){ this.picture = picture;}

    public String getName() {
        return name;
    }

    public int isIs_artifact() {
        return is_artifact;
    }

    public int getMaterial() {
        return material;
    }

    public int getPicture(){return picture;}


}
