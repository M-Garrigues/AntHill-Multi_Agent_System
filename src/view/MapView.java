package view;

import model.map.Cell;
import model.map.Map;

import java.util.ArrayList;

/**
 * Created by Florian on 24/01/2017.
 */
public class MapView {

    Map map;

    MapView(Map map){
        this.map = map
    }

    public void printMap(){
        ArrayList<Cell> arrayCell = this.map.getCells();

    }
}
