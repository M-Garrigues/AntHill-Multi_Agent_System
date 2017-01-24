package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Source extends Element {

    private boolean usable;
    private int foodStack;


    public Source(Position position, boolean usable, int foodStack) {
        super(position);
        this.usable = usable;
        this.foodStack = foodStack;
    }




    public synchronized void takeFood(){
        this.foodStack--;
        if(this.foodStack == 0) this.usable = false;
    }






    public synchronized boolean isUsable() {
        return usable;
    }

    public synchronized void setUsable(boolean usable) {
        this.usable = usable;
    }

    public synchronized int getFoodStack() {
        return foodStack;
    }

    public synchronized void setFoodStack(int foodStack) {
        this.foodStack = foodStack;
    }







}
