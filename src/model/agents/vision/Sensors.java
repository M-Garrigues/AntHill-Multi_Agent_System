package model.agents.vision;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 13/01/2017.
 */
public class Sensors implements Vision{

    public ArrayList watch(Map map, Position pos){
        ArrayList<Cell> viewedCells = new ArrayList<>();

        int seeX = pos.getX() - 1;
        int seeY = pos.getY() -1;
        Position posTemp = new Position();

        for(int i = seeX; i < seeX + 3; i++){ //This double for adds a square of 9 cells to viewedCells, with the given position as the centre.
            for(int j = seeY; j < seeY; j++){
                posTemp.setX(i);
                posTemp.setY(j);
                viewedCells.add(map.getCellPosition(posTemp));
            }
        }
        return viewedCells;
    }
}
