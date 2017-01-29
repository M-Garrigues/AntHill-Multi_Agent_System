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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public MapView(model.map.Map map){
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
                    System.out.print(ANSI_WHITE+ "#" + ANSI_RESET);
                }
                else if(actualCell.hasAnt()) {
                    System.out.print(ANSI_RED +  "A" + ANSI_RESET);
                }
                else if (actualCell.isAntHill()){
                    System.out.print(ANSI_CYAN + "x" + ANSI_RESET);
                }
                else if (actualCell.isSource()){
                    System.out.print(ANSI_GREEN + "o" + ANSI_RESET);
                }
                else if(actualCell.hasPheromone()){

                    int qt = actualCell.getPheromone().getQuantity();
                    double result = 1/2 * Math.log(qt);
                    qt = (int)result;
                    System.out.print(ANSI_BLUE + qt + ANSI_RESET);
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }

    }


    public void update(Map map){ // WILL PROBABLY HAVE TO CHANGE THIS ONE
        this.map = map;
    }
}
