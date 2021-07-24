package com.example.albionrandomequipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView  gearDescriptionTextView, letsRollTextView;
    Button drawGearButton, appStartButton;
    ImageView helmetPicture, armorPicture, footwearPicture, mainhandPicture, offhandPicture, foodPicture, potionPicture, capePicture, backpackPicture;
    Switch headgearSwitch, chestgearSwitch,feetgearSwitch, weaponSwitch,offhandSwitch,capeSwitch,foodSwitch,potionSwitch,bagSwitch;
    boolean switchFlag = true;
    Equipment headgear, cape, mainhand,chestgear, offhand, food,feetgear,potion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gearDescriptionTextView = findViewById(R.id.gearDescriptionTextView);
        letsRollTextView = findViewById(R.id.letsRollTextView);
        drawGearButton =(Button) findViewById(R.id.drawGearButton);

        helmetPicture =(ImageView) findViewById(R.id.helmetPicture);
        armorPicture = findViewById(R.id.armorPicture);
        footwearPicture = findViewById(R.id.footwearPicture);
        mainhandPicture = findViewById(R.id.mainhandPicture);
        offhandPicture = findViewById(R.id.offhandPicture);
        foodPicture = findViewById(R.id.foodPicture);
        potionPicture = findViewById(R.id.potionPicture);
        capePicture = findViewById(R.id.capePicture);
        backpackPicture = findViewById(R.id.backpackPicture);

        headgearSwitch = (Switch) findViewById(R.id.headgearSwitch);
        chestgearSwitch = (Switch) findViewById(R.id.chestgearSwitch);
        feetgearSwitch = (Switch) findViewById(R.id.feetgearSwitch);
        weaponSwitch = (Switch) findViewById(R.id.weaponSwitch);
        offhandSwitch = (Switch) findViewById(R.id.offhandSwitch);
        capeSwitch = (Switch) findViewById(R.id.capeSwitch);
        foodSwitch = (Switch) findViewById(R.id.foodSwitch);
        potionSwitch = (Switch) findViewById(R.id.potionSwitch);
        bagSwitch = (Switch) findViewById(R.id.bagSwitch);
        appStartButton =(Button) findViewById(R.id.appStartButton);



        appStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){



             /*   backpackPicture.setImageResource(R.drawable.zzz_pepe_santa);
                helmetPicture.setImageResource(R.drawable.zzz_pepe_clown);
                capePicture.setImageResource(R.drawable.zzz_pepe_1);
                mainhandPicture.setImageResource(R.drawable.zzz_pepe_excited);
                armorPicture.setImageResource(R.drawable.zzz_pepe_eyes_up);
                offhandPicture.setImageResource(R.drawable.zzz_pepe_proud);
                foodPicture.setImageResource(R.drawable.zzz_pepe_cook);
                footwearPicture.setImageResource(R.drawable.zzz_pepe_sad);
                potionPicture.setImageResource(R.drawable.zzz_pepe_sad2);
             */

                headgearSwitch.setVisibility(View.VISIBLE);
                chestgearSwitch.setVisibility(View.VISIBLE);
                feetgearSwitch.setVisibility(View.VISIBLE);
                weaponSwitch.setVisibility(View.VISIBLE);
                offhandSwitch.setVisibility(View.VISIBLE);
                capeSwitch.setVisibility(View.VISIBLE);
                foodSwitch.setVisibility(View.VISIBLE);
                potionSwitch.setVisibility(View.VISIBLE);
                bagSwitch.setVisibility(View.VISIBLE);
                letsRollTextView.setVisibility(View.VISIBLE);
                gearDescriptionTextView.setVisibility(View.VISIBLE);

                drawGearButton.setVisibility(View.VISIBLE);
                appStartButton.setVisibility(View.INVISIBLE);



            }
        });
        drawGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(!headgearSwitch.isChecked() || switchFlag){
                    headgear=drawHelmet();
                    switchFlag=false;
                }
                if(!capeSwitch.isChecked() || switchFlag) {
                    cape = drawCape();
                    switchFlag=false;
                }
                if(!chestgearSwitch.isChecked() || switchFlag) {
                    chestgear = drawArmor();
                    switchFlag=false;
                }
                if(!foodSwitch.isChecked() || switchFlag) {
                    food = drawFood();
                    switchFlag=false;
                }
                if(!feetgearSwitch.isChecked() || switchFlag) {
                    feetgear = drawfootwear();
                    switchFlag=false;
                }
                if(!potionSwitch.isChecked() || switchFlag) {
                    potion = drawPotion();
                    switchFlag=false;
                }

                if(!weaponSwitch.isChecked() && !offhandSwitch.isChecked() || switchFlag){
                    mainhand = drawWeapon();
                    if(mainhand.is_1handed){
                        offhand=drawOffhand();
                    }else
                        offhand=null;

                } else if(!weaponSwitch.isChecked() && offhandSwitch.isChecked() || switchFlag){
                    do{
                        mainhand=drawWeapon();
                        offhandPicture.setAlpha(0);
                    }while(!mainhand.is_1handed);
                } else if(weaponSwitch.isChecked() && !offhandSwitch.isChecked() || switchFlag){
                    if(mainhand.is_1handed)
                        offhand= drawOffhand();
                }



                helmetPicture.setImageResource(headgear.picture);
                armorPicture.setImageResource(chestgear.picture);
                footwearPicture.setImageResource(feetgear.picture);
                backpackPicture.setImageResource(R.drawable.bag);
                capePicture.setImageResource(cape.picture);
                potionPicture.setImageResource(potion.picture);
                foodPicture.setImageResource(food.picture);
                mainhandPicture.setImageResource(mainhand.picture);
                if (offhand != null) {
                    offhandPicture.setAlpha(255);
                    offhandPicture.setImageResource(offhand.picture);
                } else {
                    offhandPicture.setAlpha(127);
                    offhandPicture.setImageResource(mainhand.picture);

                }
                String buildDescription = "Headgear: " + headgear.name + "\n" +
                        "Chestgear: " + chestgear.name + "\n" +
                        "Feetgear: " + feetgear.name + "\n" +
                        "Cape: " + cape.name + "\n" +
                        "Food: " + food.name + "\n" +
                        "Potion: " + potion.name + "\n" +
                        "Weapon: " + mainhand.name + "\n";
                if (offhand != null)
                    buildDescription += "Offhand: " + offhand.name + "\n";

                gearDescriptionTextView.setText(buildDescription);












            }
        });
    }




    public Equipment drawHelmet(){
        ArrayList<Equipment> allHelmets = new ArrayList<Equipment>();

        allHelmets.add(new Equipment("mage cowl",0,1,R.drawable.headgear_cowl_mage));
        allHelmets.add(new Equipment("cleric cowl",0,1,R.drawable.headgear_cowl_cleric));
        allHelmets.add(new Equipment("scholar cowl",0,1,R.drawable.headgear_cowl_scholar));
        allHelmets.add(new Equipment("druid cowl",0,1,R.drawable.headgear_cowl_druid));
        allHelmets.add(new Equipment("fiend cowl",0,1,R.drawable.headgear_cowl_fiend));
        allHelmets.add(new Equipment("cultist cowl",0,1,R.drawable.headgear_cowl_cultist));
        allHelmets.add(new Equipment("royal cowl",0,1,R.drawable.headgear_cowl_royal));
        allHelmets.add(new Equipment("cowl of purity",0,1,R.drawable.headgear_cowl_ofpurity));

        allHelmets.add(new Equipment("mercenary hood",0,2,R.drawable.headgear_hood_mercenary));
        allHelmets.add(new Equipment("hunter hood",0,2,R.drawable.headgear_hood_hunter));
        allHelmets.add(new Equipment("assassin hood",0,2,R.drawable.headgear_hood_assassin));
        allHelmets.add(new Equipment("stalker hood",0,2,R.drawable.headgear_hood_stalker));
        allHelmets.add(new Equipment("hellion hood",0,2,R.drawable.headgear_hood_hellion));
        allHelmets.add(new Equipment("specter hood",0,2,R.drawable.headgear_hood_specter));
        allHelmets.add(new Equipment("royal hood",0,2,R.drawable.headgear_hood_royal));
        allHelmets.add(new Equipment("hood of tenacity",0,2,R.drawable.headgear_hood_oftenacity));

        allHelmets.add(new Equipment("soldier helmet",0,3,R.drawable.headgear_helmet_soldier));
        allHelmets.add(new Equipment("knight helmet",0,3,R.drawable.headgear_helmet_knight));
        allHelmets.add(new Equipment("guardian helmet",0,3,R.drawable.headgear_helmet_guardian));
        allHelmets.add(new Equipment("graveguard helmet",0,3,R.drawable.headgear_helmet_graveguard));
        allHelmets.add(new Equipment("demon helmet",0,3,R.drawable.headgear_helmet_demon));
        allHelmets.add(new Equipment("judicator helmet",0,3,R.drawable.headgear_helmet_judicator));
        allHelmets.add(new Equipment("royal helmet",0,3,R.drawable.headgear_helmet_royal));
        allHelmets.add(new Equipment("helmet of valor",0,3,R.drawable.headgear_helmet_ofvalor));


        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allHelmets.size());


        return allHelmets.get(rng);

        /*
            cloth cowls: mage cowl, cleric cowl, scholar cowl, druid cowl, fiend cowl, cultist cowl, royal cowl, cowl of purity
            leather hoods: mercenary hood, hunter hood, assassin hood, stalker hood, hellion hood, specter hood, royal hood, hood of tenacity
            plate helmets: soldier helmet, knight helmet, guardian helmet, graveguard helmet, demon helmet, judicator helmet, royal helmet, helmet of valor
         */


    }

    public Equipment drawArmor(){
        ArrayList<Equipment> allArmors = new ArrayList<Equipment>();

        allArmors.add(new Equipment("mage robe",0,1,R.drawable.chestgear_robe_mage));
        allArmors.add(new Equipment("cleric robe",0,1,R.drawable.chestgear_robe_cleric));
        allArmors.add(new Equipment("scholar robe",0,1,R.drawable.chestgear_robe_scholar));
        allArmors.add(new Equipment("druid robe",0,1,R.drawable.chestgear_robe_druid));
        allArmors.add(new Equipment("fiend robe",0,1,R.drawable.chestgear_robe_fiend));
        allArmors.add(new Equipment("cultist robe",0,1,R.drawable.chestgear_robe_cultist));
        allArmors.add(new Equipment("royal robe",0,1,R.drawable.chestgear_robe_royal));
        allArmors.add(new Equipment("robe of purity",0,1,R.drawable.chestgear_robe_ofpurity));
        allArmors.add(new Equipment("mercenary jacket",0,2,R.drawable.chestgear_jacket_mercenary));
        allArmors.add(new Equipment("hunter jacket",0,2,R.drawable.chestgear_jacket_hunter));
        allArmors.add(new Equipment("assassin jacket",0,2,R.drawable.chestgear_jacket_assassin));
        allArmors.add(new Equipment("stalker jacket",0,2,R.drawable.chestgear_jacket_stalker));
        allArmors.add(new Equipment("hellion jacket",0,2,R.drawable.chestgear_jacket_hellion));
        allArmors.add(new Equipment("specter jacket",0,2,R.drawable.chestgear_jacket_specter));
        allArmors.add(new Equipment("royal jacket",0,2,R.drawable.chestgear_jacket_royal));
        allArmors.add(new Equipment("jacket of tenacity",0,2,R.drawable.chestgear_jacket_oftenacity));

        allArmors.add(new Equipment("soldier armor",0,3,R.drawable.chestgear_armor_soldier));
        allArmors.add(new Equipment("knight armor",0,3,R.drawable.chestgear_armor_knight));
        allArmors.add(new Equipment("guardian armor",0,3,R.drawable.chestgear_armor_guardian));
        allArmors.add(new Equipment("graveguard armor",0,3,R.drawable.chestgear_armor_graveguard));
        allArmors.add(new Equipment("demon armor",0,3,R.drawable.chestgear_armor_demon));
        allArmors.add(new Equipment("judicator armor",0,3,R.drawable.chestgear_armor_judicator));
        allArmors.add(new Equipment("royal armor",0,3,R.drawable.chestgear_armor_royal));
        allArmors.add(new Equipment("armor of valor",0,3,R.drawable.chestgear_armor_ofvalor));

        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allArmors.size());

        return allArmors.get(rng);
    }

    public Equipment drawfootwear(){
        ArrayList<Equipment> allfootwear = new ArrayList<Equipment>();

        allfootwear.add(new Equipment("mage sandals",0,1,R.drawable.footwear_sandals_mage));
        allfootwear.add(new Equipment("cleric sandals",0,1,R.drawable.footwear_sandals_cleric));
        allfootwear.add(new Equipment("scholar sandals",0,1,R.drawable.footwear_sandals_scholar));
        allfootwear.add(new Equipment("druid sandals",0,1,R.drawable.footwear_sandals_druid));
        allfootwear.add(new Equipment("fiend sandals",0,1,R.drawable.footwear_sandals_fiend));
        allfootwear.add(new Equipment("cultist sandals",0,1,R.drawable.footwear_sandals_cultist));
        allfootwear.add(new Equipment("royal sandals",0,1,R.drawable.footwear_sandals_royal));
        allfootwear.add(new Equipment("sandals of purity",0,1,R.drawable.footwear_sandals_ofpurity));


        allfootwear.add(new Equipment("mercenary shoes",0,2,R.drawable.footwear_shoes_mercenary));
        allfootwear.add(new Equipment("hunter shoes",0,2,R.drawable.footwear_shoes_hunter));
        allfootwear.add(new Equipment("assassin shoes",0,2,R.drawable.footwear_shoes_assassin));
        allfootwear.add(new Equipment("stalker shoes",0,2,R.drawable.footwear_shoes_stalker));
        allfootwear.add(new Equipment("hellion shoes",0,2,R.drawable.footwear_shoes_hellion));
        allfootwear.add(new Equipment("specter shoes",0,2,R.drawable.footwear_shoes_specter));
        allfootwear.add(new Equipment("royal shoes",0,2,R.drawable.footwear_shoes_royal));
        allfootwear.add(new Equipment("shoes of tenacity",0,2,R.drawable.footwear_shoes_oftenacity));

        allfootwear.add(new Equipment("soldier boots",0,3,R.drawable.footwear_boots_soldier));
        allfootwear.add(new Equipment("knight boots",0,3,R.drawable.footwear_boots_knight));
        allfootwear.add(new Equipment("guardian boots",0,3,R.drawable.footwear_boots_guardian));
        allfootwear.add(new Equipment("graveguard boots",0,3,R.drawable.footwear_boots_graveguard));
        allfootwear.add(new Equipment("demon boots",0,3,R.drawable.footwear_boots_demon));
        allfootwear.add(new Equipment("judicator boots",0,3,R.drawable.footwear_boots_judicator));
        allfootwear.add(new Equipment("royal boots",0,3,R.drawable.footwear_boots_royal));
        allfootwear.add(new Equipment("boots of valor",0,3,R.drawable.footwear_boots_ofvalor));

        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allfootwear.size());

        return allfootwear.get(rng);
    }


    public Equipment drawPotion(){
        ArrayList<Equipment> allPotions = new ArrayList<Equipment>();

        allPotions.add(new Equipment("energy potion",R.drawable.potion_energypotion));
        allPotions.add(new Equipment("gigantify potion",R.drawable.potion_gigantifypotion));
        allPotions.add(new Equipment("healing potion",R.drawable.potion_healingpotion));
        allPotions.add(new Equipment("invisibility potion",R.drawable.potion_invisibilitypotion));
        allPotions.add(new Equipment("poison potion",R.drawable.potion_poisonpotion));
        allPotions.add(new Equipment("resistance potion",R.drawable.potion_resistancepotion));
        allPotions.add(new Equipment("sticky potion",R.drawable.potion_stickypotion));

        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allPotions.size());

        return allPotions.get(rng);
    }

    public Equipment drawCape(){
        ArrayList<Equipment> allCapes = new ArrayList<Equipment>();

        allCapes.add(new Equipment("bridgewatch cape",R.drawable.cape_bridgewatchcape));
        allCapes.add(new Equipment("demon cape",R.drawable.cape_demoncape));
        allCapes.add(new Equipment("fort sterling cape",R.drawable.cape_fortsterlingcape));
        allCapes.add(new Equipment("heretic cape",R.drawable.cape_hereticcape));
        allCapes.add(new Equipment("keeper cape",R.drawable.cape_keepercape));
        allCapes.add(new Equipment("lymhurst cape",R.drawable.cape_lymhurstcape));
        allCapes.add(new Equipment("martlock cape",R.drawable.cape_martlockcape));
        allCapes.add(new Equipment("morgana cape",R.drawable.cape_morganacape));
        allCapes.add(new Equipment("thetford cape",R.drawable.cape_thetfordcape));
        allCapes.add(new Equipment("undead cape",R.drawable.cape_undeadcape));

        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allCapes.size());

        return allCapes.get(rng);
    }

    public Equipment drawFood(){
        ArrayList<Equipment> allFood = new ArrayList<Equipment>();

        allFood.add(new Equipment("avalonian pork omelette",0,R.drawable.food_omelette_avalonianporkomelette));
        allFood.add(new Equipment("dusthole crab omelette",0,R.drawable.food_omelette_dustholecrabomelette));
        allFood.add(new Equipment("pork omelette",0,R.drawable.food_omelette_porkomelette));
        allFood.add(new Equipment("roast puremist snapper",0,R.drawable.food_roast_puremistsnapper));
        allFood.add(new Equipment("roast pork",0,R.drawable.food_roast_roastpork));
        allFood.add(new Equipment("avalonian beef sandwich",0,R.drawable.food_sandwich_avalonianbeefsandwich));
        allFood.add(new Equipment("thunderfall lurcher sandwich",0,R.drawable.food_sandwich_thunderfalllurchersandwich));
        allFood.add(new Equipment("blackbog clam soup",0,R.drawable.food_soup_blackbogclamsoup));
        allFood.add(new Equipment("cabbage soup",0,R.drawable.food_soup_cabbagesoup));
        allFood.add(new Equipment("avalonian beef stew",0,R.drawable.food_stew_avalonianbeefstew));
        allFood.add(new Equipment("beef stew",0,R.drawable.food_stew_beefstew));
        allFood.add(new Equipment("deadwater eel stew",0,R.drawable.food_stew_deadwatereelstew));


        Random rngForHelmets = new Random();
        int rng = rngForHelmets.nextInt(allFood.size());

        return allFood.get(rng);
    }



    public Equipment drawWeapon(){
        ArrayList<Equipment> allWeapons = new ArrayList<Equipment>();




        //############ ARCANE STAVES ####################
        allWeapons.add(new Equipment("arcane staff",0,true,1,R.drawable.weapon_arcane_arcanestaff));
        allWeapons.add(new Equipment("enigmatic staff",0,false,1,R.drawable.weapon_arcane_enigmaticstaff));
        allWeapons.add(new Equipment("even song",0,false,1,R.drawable.weapon_arcane_evensong));
        allWeapons.add(new Equipment("great arcane staff",0,false,1,R.drawable.weapon_arcane_greatarcanestaff));
        allWeapons.add(new Equipment("malevolent locus",0,false,1,R.drawable.weapon_arcane_malevolentlocus));
        allWeapons.add(new Equipment("occult staff",0,false,1,R.drawable.weapon_arcane_occultstaff));
        allWeapons.add(new Equipment("witchwork staff",0,true,1,R.drawable.weapon_arcane_witchworkstaff));

        //############ AXES ##################
        allWeapons.add(new Equipment("battleaxe",0,true,3,R.drawable.weapon_axe_battleaxe));
        allWeapons.add(new Equipment("bearpaws",0,false,3,R.drawable.weapon_axe_bearpaws));
        allWeapons.add(new Equipment("carrioncaller",0,false,3,R.drawable.weapon_axe_carrioncaller));
        allWeapons.add(new Equipment("greataxe",0,false,3,R.drawable.weapon_axe_greataxe));
        allWeapons.add(new Equipment("halberd",0,false,3,R.drawable.weapon_axe_halberd));
        allWeapons.add(new Equipment("infernal scythe",0,false,3,R.drawable.weapon_axe_infernalscythe));
        allWeapons.add(new Equipment("realmbreaker",0,false,3,R.drawable.weapon_axe_realmbreaker));

        //########### BOWS ################
        allWeapons.add(new Equipment("bow",0,false,2,R.drawable.weapon_bow_bow));
        allWeapons.add(new Equipment("bow of badon",0,false,2,R.drawable.weapon_bow_bowofbadon));
        allWeapons.add(new Equipment("long bow",0,false,2,R.drawable.weapon_bow_longbow));
        allWeapons.add(new Equipment("mistpiercer",0,false,2,R.drawable.weapon_bow_mistpiercer));
        allWeapons.add(new Equipment("wailing bow",0,false,2,R.drawable.weapon_bow_wailingbow));
        allWeapons.add(new Equipment("warbow",0,false,2,R.drawable.weapon_bow_warbow));
        allWeapons.add(new Equipment("whispering bow",0,false,2,R.drawable.weapon_bow_whisperingbow));

        //########### CROSSBOWS ###########
        allWeapons.add(new Equipment("blotcasters",0,false,3,R.drawable.weapon_crossbow_boltcasters));
        allWeapons.add(new Equipment("crossbow",0,false,3,R.drawable.weapon_crossbow_crossbow));
        allWeapons.add(new Equipment("energy shaper",0,false,3,R.drawable.weapon_crossbow_energyshaper));
        allWeapons.add(new Equipment("heavy crossbow",0,false,3,R.drawable.weapon_crossbow_heavycrossbow));
        allWeapons.add(new Equipment("light crossbow",0,true,3,R.drawable.weapon_crossbow_lightcrossbow));
        allWeapons.add(new Equipment("siege bow",0,false,3,R.drawable.weapon_crossbow_siegebow));
        allWeapons.add(new Equipment("weeping repeater",0,false,3,R.drawable.weapon_crossbow_weepingrepeater));

        //########### CURSED STAVES #######
        allWeapons.add(new Equipment("cursed skull",0,false,1,R.drawable.weapon_cursed_cursedskull));
        allWeapons.add(new Equipment("cursed staff",0,true,1,R.drawable.weapon_cursed_cursedstaff));
        allWeapons.add(new Equipment("damnation staff",0,false,1,R.drawable.weapon_cursed_damnationstaff));
        allWeapons.add(new Equipment("demonic staff",0,false,1,R.drawable.weapon_cursed_demonicstaff));
        allWeapons.add(new Equipment("great cursed staff",0,false,1,R.drawable.weapon_cursed_greatcursedstaff));
        allWeapons.add(new Equipment("lifecurse staff",0,true,1,R.drawable.weapon_cursed_lifecursestaff));
        allWeapons.add(new Equipment("shadowcaller",0,true,1,R.drawable.weapon_cursed_shadowcaller));

        //########### DAGGERS ###########
        allWeapons.add(new Equipment("black hands",0,false,2,R.drawable.weapon_dagger_blackhands));
        allWeapons.add(new Equipment("bloodletter",0,true,2,R.drawable.weapon_dagger_bloodletter));
        allWeapons.add(new Equipment("bridled fury",0,false,2,R.drawable.weapon_dagger_bridledfury));
        allWeapons.add(new Equipment("claws",0,false,2,R.drawable.weapon_dagger_claws));
        allWeapons.add(new Equipment("dagger",0,true,2,R.drawable.weapon_dagger_dagger));
        allWeapons.add(new Equipment("dagger pair",0,false,2,R.drawable.weapon_dagger_daggerpair));
        allWeapons.add(new Equipment("deathgivers",0,false,2,R.drawable.weapon_dagger_deathgivers));

        //########### FIRE STAVES #########
        allWeapons.add(new Equipment("blazing staff",0,false,1,R.drawable.weapon_fire_blazingstaff));
        allWeapons.add(new Equipment("brimstone staff",0,false,1,R.drawable.weapon_fire_brimstonestaff));
        allWeapons.add(new Equipment("dawnsong",0,false,1,R.drawable.weapon_fire_dawnsong));
        allWeapons.add(new Equipment("fire staff",0,true,1,R.drawable.weapon_fire_firestaff));
        allWeapons.add(new Equipment("great fire staff",0,false,1,R.drawable.weapon_fire_greatfirestaff));
        allWeapons.add(new Equipment("infernal staff",0,false,1,R.drawable.weapon_fire_infernalstaff));
        allWeapons.add(new Equipment("wildfire staff",0,true,1,R.drawable.weapon_fire_wildfirestaff));

        //########### FROST STAVES #########
        allWeapons.add(new Equipment("chillhowl",0,false,1,R.drawable.weapon_frost_chillhowl));
        allWeapons.add(new Equipment("frost staff",0,true,1,R.drawable.weapon_frost_froststaff));
        allWeapons.add(new Equipment("glacial staff",0,false,1,R.drawable.weapon_frost_glacialstaff));
        allWeapons.add(new Equipment("great frost staff",0,false,1,R.drawable.weapon_frost_greatfroststaff));
        allWeapons.add(new Equipment("hoarfrost staff",0,true,1,R.drawable.weapon_frost_hoarfroststaff));
        allWeapons.add(new Equipment("icicle staff",0,false,1,R.drawable.weapon_frost_iciclestaff));
        allWeapons.add(new Equipment("permafrost prism",0,false,1,R.drawable.weapon_frost_permafrostprism));

        //########### HAMMERS ############
        allWeapons.add(new Equipment("forge hammers",0,false,3,R.drawable.weapon_hammer_forgehammers));
        allWeapons.add(new Equipment("great hammer",0,false,3,R.drawable.weapon_hammer_great_hammer));
        allWeapons.add(new Equipment("grovekeeper",0,false,3,R.drawable.weapon_hammer_grovekeeper));
        allWeapons.add(new Equipment("hammer",0,true,3,R.drawable.weapon_hammer_hammer));
        allWeapons.add(new Equipment("hand of justice",0,false,3,R.drawable.weapon_hammer_handofjustice));
        allWeapons.add(new Equipment("polehammer",0,false,3,R.drawable.weapon_hammer_polehammer));
        allWeapons.add(new Equipment("tombhammer",0,false,3,R.drawable.weapon_hammer_tombhammer));

        //########### HOLY STAVES ##########
        allWeapons.add(new Equipment("holy staff",0,true,1,R.drawable.weapon_holy_holystaff));
        allWeapons.add(new Equipment("great holy staff",0,false,1,R.drawable.weapon_holy_greatholystaff));
        allWeapons.add(new Equipment("divine staff",0,false,1,R.drawable.weapon_holy_divinestaff));
        allWeapons.add(new Equipment("fallen staff",0,false,1,R.drawable.weapon_holy_fallenstaff));
        allWeapons.add(new Equipment("hallowfall",0,true,1,R.drawable.weapon_holy_hallowfall));
        allWeapons.add(new Equipment("lifetouch staff",0,true,1,R.drawable.weapon_holy_lifetouchstaff));
        allWeapons.add(new Equipment("redemption staff",0,false,1,R.drawable.weapon_holy_redemptionstaff));

        //########### MACES #############
        allWeapons.add(new Equipment("bedrock mace",0,true,3,R.drawable.weapon_mace_bedrockmace));
        allWeapons.add(new Equipment("camlann mace",0,false,3,R.drawable.weapon_mace_camlannmace));
        allWeapons.add(new Equipment("heavy mace",0,false,3,R.drawable.weapon_mace_heavymace));
        allWeapons.add(new Equipment("incubus mace",0,true,3,R.drawable.weapon_mace_incubusmace));
        allWeapons.add(new Equipment("mace",0,true,3,R.drawable.weapon_mace_mace));
        allWeapons.add(new Equipment("morning star",0,false,3,R.drawable.weapon_mace_morningstar));
        allWeapons.add(new Equipment("oathkeepers",0,false,3,R.drawable.weapon_mace_oathkeepers));

        //########### NATURE STAVES ########
        allWeapons.add(new Equipment("blight staff",0,false,2,R.drawable.weapon_nature_blightstaff));
        allWeapons.add(new Equipment("druidic staff",0,true,2,R.drawable.weapon_nature_druidicstaff));
        allWeapons.add(new Equipment("great nature staff",0,false,2,R.drawable.weapon_nature_greatnaturestaff));
        allWeapons.add(new Equipment("ironroot staff",0,false,2,R.drawable.weapon_nature_ironrootstaff));
        allWeapons.add(new Equipment("nature staff",0,true,2,R.drawable.weapon_nature_naturestaff));
        allWeapons.add(new Equipment("rampant staff",0,false,2,R.drawable.weapon_nature_rampantstaff));
        allWeapons.add(new Equipment("wild staff",0,false,2,R.drawable.weapon_nature_wildstaff));

        //########### QUARTERSTAVES #########
        allWeapons.add(new Equipment("black monk stave",0,false,2,R.drawable.weapon_quarterstaff_blackmonkstave));
        allWeapons.add(new Equipment("double bladed staff",0,false,2,R.drawable.weapon_quarterstaff_doublebladedstaff));
        allWeapons.add(new Equipment("grailseeker",0,false,2,R.drawable.weapon_quarterstaff_grailseeker));
        allWeapons.add(new Equipment("iron-clad staff",0,false,2,R.drawable.weapon_quarterstaff_ironcladstaff));
        allWeapons.add(new Equipment("quarterstaff",0,false,2,R.drawable.weapon_quarterstaff_quarterstaff));
        allWeapons.add(new Equipment("soul scythe",0,false,2,R.drawable.weapon_quarterstaff_soulscythe));
        allWeapons.add(new Equipment("staff of balance",0,false,2,R.drawable.weapon_quarterstaff_staffofbalance));

        //########### SPEARS ##############
        allWeapons.add(new Equipment("daybreaker",0,false,2,R.drawable.weapon_spear_daybreaker));
        allWeapons.add(new Equipment("glaive",0,false,2,R.drawable.weapon_spear_glaive));
        allWeapons.add(new Equipment("heron spear",0,true,2,R.drawable.weapon_spear_heronspear));
        allWeapons.add(new Equipment("pike",0,false,2,R.drawable.weapon_spear_pike));
        allWeapons.add(new Equipment("spear",0,true,2,R.drawable.weapon_spear_spear));
        allWeapons.add(new Equipment("spirithunter",0,false,2,R.drawable.weapon_spear_spirithunter));
        allWeapons.add(new Equipment("trinity spear",0,false,2,R.drawable.weapon_spear_trinityspear));

        //########### SWORDS #############
        allWeapons.add(new Equipment("broadsword",0,true,3,R.drawable.weapon_sword_broadsword));
        allWeapons.add(new Equipment("carving sword",0,false,3,R.drawable.weapon_sword_carvingsword));
        allWeapons.add(new Equipment("clarent blade",0,true,3,R.drawable.weapon_sword_clarentblade));
        allWeapons.add(new Equipment("claymore",0,false,3,R.drawable.weapon_sword_claymore));
        allWeapons.add(new Equipment("dual swords",0,false,3,R.drawable.weapon_sword_dualswords));
        allWeapons.add(new Equipment("galatine pair",0,false,3,R.drawable.weapon_sword_galatinepair));
        allWeapons.add(new Equipment("kingmaker",0,false,3,R.drawable.weapon_sword_kingmaker));












        Random rngForHelmets = new Random();
        int intRngForHelmets = rngForHelmets.nextInt(allWeapons.size());





        return allWeapons.get(intRngForHelmets);

    }

    public Equipment drawOffhand(){
        //############################################# OFFHANDS ###################################################
        ArrayList<Offhands> allOffhands = new ArrayList<Offhands>();
        //############ Shields #########
        allOffhands.add(new Offhands("astral aegis",0,3,R.drawable.offhand_shield_astral_aegis));
        allOffhands.add(new Offhands("caitiff shield",0,3,R.drawable.offhand_shield_caitiff_shield));
        allOffhands.add(new Offhands("facebreaker",0,3,R.drawable.offhand_shield_facebreaker));
        allOffhands.add(new Offhands("sarcophagus",0,3,R.drawable.offhand_shield_sarcophagus));
        allOffhands.add(new Offhands("shield",0,3,R.drawable.offhand_shield_shield));

        //############ TORCHES #########
        allOffhands.add(new Offhands("cryptcandle",0,2,R.drawable.offhand_torch_cryptcandle));
        allOffhands.add(new Offhands("leering cane",0,2,R.drawable.offhand_torch_leeringcane));
        allOffhands.add(new Offhands("mistcaller",0,2,R.drawable.offhand_torch_mistcaller));
        allOffhands.add(new Offhands("sacred scepter",0,2,R.drawable.offhand_torch_sacred_scepter));
        allOffhands.add(new Offhands("torch",0,2,R.drawable.offhand_torch_torch));

        //############ TOMES ###########
        allOffhands.add(new Offhands("celestial censer",0,1,R.drawable.offhand_tome_celestial_censer));
        allOffhands.add(new Offhands("eye of secrets",0,1,R.drawable.offhand_tome_eyeofsecrets));
        allOffhands.add(new Offhands("muisak",0,1,R.drawable.offhand_tome_muisak));
        allOffhands.add(new Offhands("taproot",0,1,R.drawable.offhand_tome_taproot));
        allOffhands.add(new Offhands("tome of spells",0,1,R.drawable.offhand_tome_tomeofspells));


        Random rngForOffhands = new Random();
        int intRngForOffhands = rngForOffhands.nextInt(allOffhands.size());

        return allOffhands.get(intRngForOffhands);
    }




}