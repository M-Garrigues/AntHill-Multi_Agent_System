package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Pheromone extends Element{

    private int quantity;

    public Pheromone(Position position, int quantity) {
        super(position);
        this.quantity = quantity;
    }


    public void tick(){
        this.quantity--;
    }

    public void add(int quantity){
        this.quantity += quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
