package model.agents.mobileAgent.movement;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Florian on 13/01/2017.
 */
public class OneStep implements Movement{

    public ArrayList move(Map map, Position pos){
        ArrayList<Cell> cellMove = new ArrayList<>();

        //cellMove.add(new Cell(new Position(1,1)));

        int moveX = pos.getX() - 1;
        int moveY = pos.getY() -1;
        Position posTemp = new Position();

        for(int i = moveX; i < moveX + 3; i++){ //This double for adds a square of 9 cells to viewedCells, with the given position as the centre.
            for(int j = moveY; j < moveY + 3; j++){
                posTemp.setX(i);
                posTemp.setY(j);
                Cell tempCell = map.getCellPosition(posTemp);
                if (!tempCell.isObstacle()) {
                    cellMove.add(tempCell);
                }
            }
        }
        return cellMove;
    }
}
