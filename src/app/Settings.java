package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Coded by Mathieu GARRIGUES on 11/01/2017.
 */
public class Settings {

    private int mapSizeX;
    private int mapSizeY;

    private int nbSources;
    private int foodStackMin;
    private int foodStackMax;

    private int nbAnts;



    //=============================================


    public Settings(){}

    public void readFile (){
        //Reading from file map
        //Line 1 : map size X
        //Line 2 : map size Y
        //Line 3 : number of ants
        //Line 4 : minimum food supply on a source
        //Line 5 : maximum food supply on a source

        String filename = "data/map/settings";
        FileReader fileReader;
        BufferedReader bufferReader;
        String currentLine;
        int counter = 0;
        int value;
        try {
            fileReader = new FileReader(filename);
            bufferReader = new BufferedReader(fileReader);

            while ((currentLine = bufferReader.readLine()) != null) {
                value =  Integer.parseInt(currentLine);
                switch (counter) {
                    case 0:
                        this.mapSizeX = value;
                    case 1:
                        this.mapSizeY = value;
                    case 2:
                        this.nbAnts = value;
                    case 3:
                        this.foodStackMin = value;
                    case 4:
                        this.foodStackMax = value;
                }
                counter += 1;
           }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public int getMapSizeX() {
        return mapSizeX;
    }

    public void setMapSizeX(int mapSizeX) {
        this.mapSizeX = mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }

    public void setMapSizeY(int mapSizeY) {
        this.mapSizeY = mapSizeY;
    }

    public int getNbSources() {
        return nbSources;
    }

    public void setNbSources(int nbSources) {
        this.nbSources = nbSources;
    }

    public int getFoodStackMin() {
        return foodStackMin;
    }

    public void setFoodStackMin(int foodStackMin) {
        this.foodStackMin = foodStackMin;
    }

    public int getFoodStackMax() {
        return foodStackMax;
    }

    public void setFoodStackMax(int foodStackMax) {
        this.foodStackMax = foodStackMax;
    }

    public int getNbAnts() {
        return nbAnts;
    }

    public void setNbAnts(int nbAnts) {
        this.nbAnts = nbAnts;
    }


}
