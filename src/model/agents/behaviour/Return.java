package model.agents.behaviour;

import model.agents.Agent;
import model.agents.mobileAgent.MobileAgent;
import model.agents.mobileAgent.ant.Ant;
import model.map.Cell;
import view.ErrorView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Mathieu on 27/01/2017.
 */
public class Return implements Behaviour {

    public void act(Agent agent){

        Ant  ant = new Ant();

        try{
            ant = (Ant)agent;
        }
        catch (Exception e){
            ErrorView.textError("Returning agent is not an Ant.");
        }
        finally {

        }

        Cell startCell = agent.getMap().getCellPosition(agent.getPosition());

        Cell endCell = new Cell();

        ArrayList<Cell> movableCells = ant.getMoveCells();



        for (Cell cellPath: ant.getPath()) {

            if(movableCells.contains(cellPath)){
                endCell = cellPath;
            }
        }



        ant.move(startCell, endCell);

    }
}
