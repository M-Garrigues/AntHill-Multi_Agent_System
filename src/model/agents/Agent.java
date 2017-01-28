package model.agents;

import model.Position;
import model.agents.behaviour.Behaviour;
import model.agents.behaviour.Fetch;
import model.agents.behaviour.Stop;
import model.agents.vision.Godlike;
import model.agents.vision.Vision;
import model.elements.Element;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Agent extends Element implements Runnable{

    protected Vision vision;
    protected Behaviour behaviour;
    private ArrayList<Cell> perceivedCells;
    private Map map;



    public Agent() {
        super(new Position(0,0));
        vision = new Godlike();
        behaviour = new Fetch();
        perceivedCells = new ArrayList<>();
        map = new Map(0,0);
    }

    public Agent(Position position) {
        super(position);
        vision = new Godlike();
        behaviour = new Fetch();
        perceivedCells = new ArrayList<>();
        map = new Map(0,0);
    }


    public void run(){}



    public Vision getVision() {
        return vision;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public ArrayList<Cell> getPerceivedCells() {
        return perceivedCells;
    }

    public void setPerceivedCells(ArrayList<Cell> perceivedCells) {
        this.perceivedCells = perceivedCells;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


}
