package model.agents;

import model.Position;
import model.agents.vision.Vision;
import model.elements.Element;
import model.map.Cell;

import java.util.ArrayList;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Agent extends Element implements Runnable{

    protected Vision vision;
    ArrayList<Cell> perceivedCells;



    public Agent(Position position) {
        super(position);
    }


    public void run(){}



    public Vision getVision() {
        return vision;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

    public ArrayList<Cell> getPerceivedCells() {
        return perceivedCells;
    }

    public void setPerceivedCells(ArrayList<Cell> perceivedCells) {
        this.perceivedCells = perceivedCells;
    }


}
