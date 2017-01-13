package model.agents.vision;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public interface Vision {

    public ArrayList<Cell> watch(Map map, Position pos);

}
