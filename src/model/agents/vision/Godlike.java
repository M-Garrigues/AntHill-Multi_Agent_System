package model.agents.vision;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public class Godlike implements Vision{ // This vision class allows the agent to see the whole map, without limitations.

    public ArrayList watch(Map map, Position pos) {
        ArrayList<Cell> viewedCells = new ArrayList<Cell>();
        viewedCells = map.getCells();
        return viewedCells;
    }
}