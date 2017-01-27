package model.agents.actions;

import model.agents.mobileAgent.MobileAgent;
import model.map.Cell;

/**
 * Created by Mathieu on 27/01/2017.
 */
public class ActionMove {

    private Cell cellStart;
    private Cell cellEnd;


    public void doAction(MobileAgent agent){
        agent.move(cellStart, cellEnd);
    }



    public ActionMove(Cell cellStart, Cell cellEnd){
        this.cellEnd = cellEnd;
        this.cellStart = cellStart;
    }
}
