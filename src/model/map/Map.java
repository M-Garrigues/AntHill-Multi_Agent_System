package model.map;

import app.Settings;
import model.Position;
import model.elements.AntHill;
import model.elements.Obstacle;
import model.elements.Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
/*
    public void genMap (Settings settings){
        this.sizeX = settings.getMapSizeX();
        this.sizeY = settings.getMapSizeY();

        //Initializing every cell from the map
        for (int i = 0; i < this.sizeX ; i++){
            for (int j = 0; j < this.sizeY ; j++){
                Position newPos = new Position (i,j);
                Cell newCell = new Cell (newPos);
                if ((i == 0) || (i == this.sizeX-1) || (j == 0) || (j == this.sizeY-1)){
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
        System.out.println("Position de la AntHill : " + posXAntHill + " , " + posYAntHill);

        // Adding Sources to the map
        for (int i = 0; i < settings.getNbSources(); i++){
            addSourceMap(settings);
        }
    }
*/
    public void loadMap (Settings settings){

        this.sizeX = settings.getMapSizeX();
        this.sizeY = settings.getMapSizeY();

        String filename = "data/map/map.txt";
        FileReader fileReader;
        BufferedReader bufferReader;
        String currentLine;
        char[] charLine;
        char charCell;
        int lineNumber = 0;
        int intLine;

        try {
            fileReader = new FileReader(filename);
            bufferReader = new BufferedReader(fileReader);


            while (lineNumber < this.sizeY){
                System.out.println("-----------------" + lineNumber + "--------------");
                currentLine = bufferReader.readLine();
                charLine = currentLine.toCharArray();

                for (int k = 0; k < this.sizeX ; k++){

                    System.out.println(k + " : " + charLine[k]);

                    charCell = charLine[k];
                    Position actualPosition = new Position(k,lineNumber);
                    Cell actualCell = new Cell (actualPosition);

                    //this.cells.add(actualCell);
                    switch (charCell){
                        case '0' :
                            //Empty cell
                            this.cells.add(actualCell);
                            break;
                        case '1' :
                            //Wall
                            Obstacle newObstacle = new Obstacle (actualPosition);
                            actualCell.addElement(newObstacle);
                            this.cells.add(actualCell);
                            break;
                        case '2':
                            //AntHill
                            AntHill newAntHill = new AntHill(actualPosition, settings.getNbAnts());
                            actualCell.addElement(newAntHill);
                            this.cells.add(actualCell);
                            break;
                        case '3':
                            //Source
                            int foodStack = settings.getFoodStackMin() + (int)(Math.random() * (settings.getFoodStackMax()));
                            Source newSource = new Source (actualPosition,true,foodStack);
                            actualCell.addElement(newSource);
                            this.cells.add(actualCell);
                            break;
                    }
                }
                lineNumber ++;
            }

            this.printMap();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void printMap (){
        for (int i=0; i < this.getSizeY(); i++){

            System.out.println("\n\n ===== " + i + " =====\n");

            for (int j=0; j < this.getSizeX(); j++){

                this.getCellPosition(new Position(j,i)).printCell();
            }
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
