package model.agents.mobileAgent.movement;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Florian on 13/01/2017.
 */
public interface Movement {

    /* The movement interface of a MobileAgent defines how it can move on the map.
        Its function, move, returns all the cells an agent could go on in one go.
     */

    public ArrayList<Cell> move (Map map, Position position);
}
