package model.agents.mobileAgent.ant;

import model.Position;
import model.agents.mobileAgent.MobileAgent;
import model.agents.mobileAgent.movement.Movement;
import model.agents.mobileAgent.movement.OneStep;
import model.agents.vision.Sensors;
import model.agents.vision.Vision;
import model.map.Map;

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

    public Ant(Map map, Position antHillPos){
        super();

        this.setMap(map);
        this.setPosition(antHillPos);
        this.movement = new OneStep();
        this.vision = new Sensors();
    }

    public Ant(Map map, Position position, Movement movement, Vision vision){
        super(position, movement, vision);
        this.setMap(map);
    }


    @Override
    public void run() {

        Position position = this.getPosition();
        Map map = this.getMap();


        this.setPerceivedCells(this.vision.watch(map, position));

        this.setMoveCells(this.movement.move(map, position));

        System.out.println("======= "+ Thread.currentThread().getName() +"  ====  Mon id est "+ this.getClass() + " et ma position " + position.getX() +";"+ position.getY());
        this.move(map.getCellPosition(this.getPosition()), map.getCellPosition(new Position(position.getX() + 1, position.getY())));

    }

    public boolean hasFood() {
        return hasFood;
    }

    public void takeFood(boolean hasFood) {
        this.hasFood = true;
    }

    public void dropFood(boolean hasFood) {
        this.hasFood = false;
    }

    public ArrayList<Position> getPath() {
        return path;
    }

    public void setPath(ArrayList<Position> path) {
        this.path = path;
    }
}
