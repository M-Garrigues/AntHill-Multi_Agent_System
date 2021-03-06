package model.agents.vision;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public class Blind implements Vision{ // This vision class just gives the agent the cell it is on.

    public ArrayList watch(Map map, Position pos) {
        ArrayList<Cell> viewedCells = new ArrayList<Cell>();
        viewedCells.add(map.getCellPosition(pos));
        return viewedCells;
    }
}
