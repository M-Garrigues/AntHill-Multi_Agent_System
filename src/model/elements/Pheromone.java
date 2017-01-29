package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Pheromone extends Element{

    private int quantity; // The quantity of pheromone on a cell.

    public Pheromone(Position position, int quantity) {
        super(position);
        this.quantity = quantity;
    }

    public synchronized void tick(){
        this.quantity--;
    }

    public synchronized void add(int quantity){
        this.quantity += quantity;
    }


    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

