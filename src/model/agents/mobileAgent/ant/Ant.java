package model.agents.mobileAgent.ant;

import model.Position;
import model.agents.mobileAgent.MobileAgent;
import model.agents.mobileAgent.movement.Movement;
import model.agents.vision.Vision;

import java.util.ArrayList;

/**
 * Created by Mathieu on 15/01/2017.
 */
public class Ant extends MobileAgent{

    private boolean hasFood;
    private ArrayList<Position> path;

    public Ant (){
        super();
        hasFood = false;
        path = new ArrayList<>();
    }

    public Ant(Position position, Movement movement, Vision vision){
        super(position, movement, vision);
    }


    @Override
    public void run() {
        System.out.println("======= "+ Thread.currentThread() +"  ====  Mon id est "+ this.getClass().getName() + " et ma position " + this.getPosition().getX() + this.getPosition().getY());
    }

    public boolean hasFood() {
        return hasFood;
    }

    public void takeFood(boolean hasFood) {
        this.hasFood = true;
    }

    public void giveFood(boolean hasFood) {
        this.hasFood = false;
    }

    public ArrayList<Position> getPath() {
        return path;
    }

    public void setPath(ArrayList<Position> path) {
        this.path = path;
    }
}
