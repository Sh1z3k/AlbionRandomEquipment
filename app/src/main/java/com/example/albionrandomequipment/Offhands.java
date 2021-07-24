package com.example.albionrandomequipment;

public class Offhands extends Equipment{

    String type_of_offhand; //where 1 is book, 2 is torch, 3 is shield

    public Offhands(String name,int is_artifact,int type_of_offhand, int picture) {
        super(name, is_artifact,type_of_offhand,picture);
    }

}
