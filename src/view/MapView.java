package view;

import model.Position;
import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Florian on 24/01/2017.
 */
public class MapView {

    Map map;

    public MapView(Map map){
        this.map = map;
    }

    public void printMap(){
        ArrayList<Cell> arrayCell = this.map.getCells();
        int sizeX = this.map.getSizeX();
        int sizeY = this.map.getSizeY();

        for (int i = 0;  i < sizeY; i++){
            for (int j = 0; j < sizeX; j++){

                Position actualPosition = new Position (j,i);
                Cell actualCell = this.map.getCellPosition(actualPosition);

                if (actualCell.isObstacle()){
                    System.out.print("#");
                }
                else if (actualCell.isAntHill()){
                    System.out.print("x");
                }
                else if (actualCell.isSource()){
                    System.out.print("o");
                }
                else{
                    System.out.print(" ");
                }
            }

            System.out.print("\n");
        }

    }
}
