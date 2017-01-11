package app;

/**
 * Coded by Mathieu GARRIGUES on 11/01/2017.
 */
public class Settings {

    private int mapSizeX = 10;
    private int mapSizeY = 10;

    private int nbSources = 3;
    private int foodStackMin = 5;
    private int foodStackMax = 20;

    private int nbObstacles = 15;

    private int nbAnts = 10;



    //=============================================


    public Settings(){}


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

    public int getNbObstacles() {
        return nbObstacles;
    }

    public void setNbObstacles(int nbObstacles) {
        this.nbObstacles = nbObstacles;
    }

    public int getNbAnts() {
        return nbAnts;
    }

    public void setNbAnts(int nbAnts) {
        this.nbAnts = nbAnts;
    }


}
