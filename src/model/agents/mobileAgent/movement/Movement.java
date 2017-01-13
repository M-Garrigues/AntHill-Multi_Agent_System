package model.agents.mobileAgent.movement;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Florian on 13/01/2017.
 */
public interface Movement {
    public ArrayList<Cell> move (Map map, Position position);
}
