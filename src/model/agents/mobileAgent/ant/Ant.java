package model.agents.mobileAgent.ant;

import model.Position;
import model.agents.mobileAgent.MobileAgent;
import model.agents.mobileAgent.movement.Movement;
import model.agents.mobileAgent.movement.OneStep;
import model.agents.vision.Sensors;
import model.agents.vision.Vision;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Mathieu on 15/01/2017.
 */
public class Ant extends MobileAgent{

    private boolean hasFood;
    private ArrayList<Cell> path;

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
        this.path = new ArrayList<Cell>();
    }






    public void run2() {

        Position position = this.getPosition();
        Map map = this.getMap();


        this.setPerceivedCells(this.vision.watch(map, position));

        this.setMoveCells(this.movement.move(map, position));

        position = new Position(position.getX() + 1, position.getY());
        this.move(map.getCellPosition(this.getPosition()), map.getCellPosition(position));

        this.setPosition(position);

        System.out.println("======= "+ Thread.currentThread().getName() +"  ====  Mon id est "+ this.getClass() + " et ma position " + position.getX() +";"+ position.getY());
    }


    @Override
    public void run() {

        Position position = this.getPosition();
        Map map = this.getMap();


        this.setPerceivedCells(this.vision.watch(map, position));

        if (this.getPerceivedCells().isEmpty()) {
            System.out.println("Pas de cases vues!");
        }

        this.setMoveCells(this.movement.move(map, position));

        if (this.getMoveCells().isEmpty()) {
            System.out.println("Pas de déplacement possible!");
        }


        this.behaviour.act(this);

    }





    public boolean hasFood() {
        return hasFood;
    }

    public void takeFood() {
        this.hasFood = true;
    }

    public void dropFood() {
        this.hasFood = false;
    }

    public ArrayList<Cell> getPath() {
        return path;
    }

    public void setPath(ArrayList<Cell> path) {
        this.path = path;
    }
}
