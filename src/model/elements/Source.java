package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Source extends Element {

    boolean usable;
    int foodStack;

    public Source(Position position, boolean usable, int foodStack) {
        super(position);
        this.usable = usable;
        this.foodStack = foodStack;
    }




    public void takeFood(){
        this.foodStack--;
        if(this.foodStack == 0) this.usable = false;
    }






    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public int getFoodStack() {
        return foodStack;
    }

    public void setFoodStack(int foodStack) {
        this.foodStack = foodStack;
    }







}
