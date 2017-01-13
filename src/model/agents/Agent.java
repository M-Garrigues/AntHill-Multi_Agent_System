package model.agents;

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



    public void run(){}
}
