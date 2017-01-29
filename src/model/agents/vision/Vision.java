package model.agents.vision;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public interface Vision {

    /*
        The Vision interface is what gives the Agent the cells it can interact with.
        For example, is an Agent Vision is Godlike, it will be able to "have" and analyze the whole map.
        If it has Sensors, it will only have in parameter the 8 cells around it.
     */

    public ArrayList<Cell> watch(Map map, Position pos);

}
