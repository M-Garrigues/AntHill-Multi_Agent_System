package model.map;

import model.Position;
import model.elements.Obstacle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Map {

    private ArrayList<ElementList> elements;
    private ArrayList<AgentList> agents;
    private ArrayList<Cell> cells;

    private int sizeX;
    private int sizeY;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.elements = new ArrayList<ElementList>();
        this.agents = new ArrayList<AgentList>();
        this.cells = new ArrayList<Cell>();
    }

    public Map genMap (Map map){
        for (int i = 0; i < map.sizeX ; i++){
            for (int j = 0; j < map.sizeY ; i++){
                Position newPos = new Position (i,j);
                if ((i == 0) || (i == map.sizeX) || (j == 0) || (j == map.sizeY)){
                    Cell newCell = new Cell (newPos);
                    Obstacle newObstacle = new Obstacle (newPos);
                    newCell.addElement(newObstacle);
                    map.cells.add(newCell);
                }
                else {
                    Cell newCell = new Cell (newPos);
                    map.cells.add(newCell);
                }
            }
        }
        return map;
    }

    public void readFile (){
        String filename = "data/map/map.txt";
        FileReader fileReader;
        BufferedReader bufferReader;
        String currentLine;
        try {
            fileReader = new FileReader(filename);
            bufferReader = new BufferedReader(fileReader);

            while ((currentLine = bufferReader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printMap (Map map){
        int numberCells = map.sizeX * map.sizeY;
        for (int i = 0 ; i < numberCells ; i++) {
            System.out.println("Position :" + map.cells.get(i).getPosition());
        }
    }




    public ArrayList<ElementList> getElements() {
        return elements;
    }

    public void setElements(ArrayList<ElementList> elements) {
        this.elements = elements;
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
