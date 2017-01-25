package model.agents.mobileAgent;

import model.Position;
import model.agents.Agent;
import model.agents.mobileAgent.movement.Movement;
import model.agents.mobileAgent.movement.OneStep;
import model.agents.vision.Vision;
import model.map.Cell;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public class MobileAgent extends Agent{

    protected Movement movement ;
    private ArrayList<Cell> moveCells;

    public MobileAgent() {
        super();
        Movement movement = new OneStep();
        this.movement = movement;
    }

    public MobileAgent(Position position, Movement movement, Vision vision) {
        super(position);
        this.movement = movement;
        this.vision = vision;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public ArrayList<Cell> getMoveCells() {
        return moveCells;
    }

    public void setMoveCells(ArrayList<Cell> moveCells) {
        this.moveCells = moveCells;
    }
}
