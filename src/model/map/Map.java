package model.map;

import app.Settings;
import model.Position;
import model.elements.AntHill;
import model.elements.Element;
import model.elements.Obstacle;
import model.elements.Source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Map {

    private ArrayList<AgentList> agents;
    private ArrayList<Cell> cells;

    private int sizeX;
    private int sizeY;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.agents = new ArrayList<AgentList>();
        this.cells = new ArrayList<Cell>();
    }

    public Map genMap (Map map, Settings settings){
        map.sizeX = settings.getMapSizeX();
        map.sizeY = settings.getMapSizeY();

        //Initializing every cell from the map
        for (int i = 0; i < map.sizeX ; i++){
            for (int j = 0; j < map.sizeY ; j++){
                Position newPos = new Position (i,j);
                Cell newCell = new Cell (newPos);
                if ((i == 0) || (i == map.sizeX) || (j == 0) || (j == map.sizeY)){
                    Obstacle newObstacle = new Obstacle (newPos);
                    newCell.addElement(newObstacle);
                }
                map.cells.add(newCell);
            }
        }

        // Adding Anthill to the map
        Position posAntHill;
        int posXAntHill = 1 + (int)(Math.random() * (map.sizeX-2));
        int posYAntHill = 1 + (int)(Math.random() * (map.sizeY-2));
        posAntHill = new Position(posXAntHill,posYAntHill);
        Cell cellAntHill;
        cellAntHill = getCellPosition(posAntHill);
        AntHill antHill = new AntHill(posAntHill, settings.getNbAnts());
        cellAntHill.addElement(antHill);

        // Adding Sources to the map
        for (int i = 0; i < settings.getNbSources(); i++){
            addSourceMap(settings);
        }

        return map;
    }

    public void addSourceMap (Settings settings){
            Position posSource;
            Cell cellSource;
            do {
                int posXSource = 1 + (int) (Math.random() * (settings.getMapSizeX() - 2));
                int posYSource = 1 + (int) (Math.random() * (settings.getMapSizeY() - 2));
                posSource = new Position(posXSource,posYSource);
                cellSource = getCellPosition(posSource);
            }while (cellSource == null);
            int foodStack = settings.getFoodStackMin() + (int)(Math.random() * (settings.getFoodStackMax()));


            Source source = new Source(posSource,true,foodStack);
            cellSource.addElement(source);
    }


    public void printMap (){
        int numberCells = this.sizeX * this.sizeY;
        for (int i = 0 ; i < numberCells ; i++) {
            System.out.println("Position :" + this.cells.get(i).getPosition());
        }
    }
    
    public Cell getCellPosition (Position position){
        int posX = position.getX();
        int posY = position.getY();
        int index = posY*this.sizeX+ posX;
        return this.cells.get(index);
    }



    public ArrayList<AgentList> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<AgentList> agents) {
        this.agents = agents;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

}
