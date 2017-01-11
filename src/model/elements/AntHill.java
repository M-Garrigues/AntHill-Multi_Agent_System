package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class AntHill extends Element {

    private int totalAnts;
    private int actualAnts;
    private int foodStack;



    public AntHill(Position position, int totalAnts) {
        super(position);
        this.totalAnts = totalAnts;
        this.actualAnts = totalAnts;
        this.foodStack = 0;
    }




    /*public Ant sendAnt(){

        try{
            if(actualAnts != 0){
                this.actualAnts--;
                Ant ant = new Ant(this.getPosition());
            }
            return ant;
        }
        catch (Exception e){
            ErrorView.textError("Error while trying to send new ant."); //    TO BE DELETED AFTER DEBUGGING.
        }

    }*/


    public void getFood(){
        this.foodStack++;
    }







    public int getTotalAnts() {
        return totalAnts;
    }

    public void setTotalAnts(int totalAnts) {
        this.totalAnts = totalAnts;
    }

    public int getActualAnts() {
        return actualAnts;
    }

    public void setActualAnts(int actualAnts) {
        this.actualAnts = actualAnts;
    }

    public int getFoodStack() {
        return foodStack;
    }

    public void setFoodStack(int foodStack) {
        this.foodStack = foodStack;
    }
}
