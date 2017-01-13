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

    public void genMap (Settings settings){
        this.sizeX = settings.getMapSizeX();
        this.sizeY = settings.getMapSizeY();

        //Initializing every cell from the map
        for (int i = 0; i < this.sizeX ; i++){
            for (int j = 0; j < this.sizeY ; j++){
                Position newPos = new Position (i,j);
                Cell newCell = new Cell (newPos);
                if ((i == 0) || (i == this.sizeX) || (j == 0) || (j == this.sizeY)){
                    Obstacle newObstacle = new Obstacle (newPos);
                    newCell.addElement(newObstacle);
                }
                this.cells.add(newCell);
            }
        }

        // Adding Anthill to the map
        Position posAntHill;
        int posXAntHill = 1 + (int)(Math.random() * (this.sizeX-2));
        int posYAntHill = 1 + (int)(Math.random() * (this.sizeY-2));
        posAntHill = new Position(posXAntHill,posYAntHill);
        Cell cellAntHill;
        cellAntHill = getCellPosition(posAntHill);
        AntHill antHill = new AntHill(posAntHill, settings.getNbAnts());
        cellAntHill.addElement(antHill);

        // Adding Sources to the map
        for (int i = 0; i < settings.getNbSources(); i++){
            addSourceMap(settings);
        }
    }

    public void addSourceMap (Settings settings){
            Position posSource;
            Cell cellSource;
            int posXSource,posYSource;
            do {
                posXSource = 1 + (int) (Math.random() * (settings.getMapSizeX() - 2));
                posYSource = 1 + (int) (Math.random() * (settings.getMapSizeY() - 2));
                posSource = new Position(posXSource,posYSource);
                cellSource = getCellPosition(posSource);
            }while (cellSource == null);
            System.out.println("Position de la source : " + posXSource + " , " + posYSource);
            int foodStack = settings.getFoodStackMin() + (int)(Math.random() * (settings.getFoodStackMax()));


            Source source = new Source(posSource,true,foodStack);
            cellSource.addElement(source);
    }


    public void printMap (){
        int sizeTab = this.sizeX*this.sizeY;
        for (int i=0; i < sizeTab; i++){
            this.cells.get(i).printCell();
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
