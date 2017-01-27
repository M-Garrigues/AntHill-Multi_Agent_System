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
    public void loadMap (){

        String filename = "data/map/maptext.txt";
        FileReader fileReader;
        BufferedReader bufferedReader;

        String currentLine;
        int counter = 0; //Compte les lignes lues
        int line = 0;
        int numberSources = 0;
        ArrayList<Integer> listFoodStack = new ArrayList<Integer>();


        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null){

                switch (counter){

                    case 0 :
                        //Size X
                        int sizeX = Integer.parseInt(currentLine);
                        this.sizeX = sizeX;
                        break;

                    case 1 :
                        //Size Y
                        int sizeY = Integer.parseInt(currentLine);
                        this.sizeY = sizeY;
                        break;

                    case 2 :
                        //Number of sources
                        numberSources = Integer.parseInt(currentLine);
                        break;

                    case 3 :
                        //Food Stack for all sources
                        listFoodStack = foodStack(currentLine);
                        break;

                    default :
                        numberSources = InitializeElements(currentLine,line,listFoodStack,numberSources);
                        line++;
                        break;
                }
                counter ++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public int InitializeElements (String currentLine, int line,ArrayList<Integer> listFoodStack,int numberSources){

        char[] charLine;
        char charCell;

        charLine = currentLine.toCharArray();

        for (int i=0;i<charLine.length;i++){

            charCell = charLine[i];
            Position actualPosition = new Position(i,line);
            Cell actualCell = new Cell(actualPosition);

            switch(charCell){

                case '#' :
                    Obstacle obstacle = new Obstacle(actualPosition);
                    actualCell.addElement(obstacle);
                    break;

                case 'x' :
                    AntHill antHill = new AntHill(actualPosition,40);
                    actualCell.addElement(antHill);
                    break;

                case 'o' :
                    int foodStack = listFoodStack.get(numberSources - 1);
                    Source source = new Source(actualPosition,true,foodStack);
                    actualCell.addElement(source);
                    numberSources--;
                    break;
            }

            this.cells.add(actualCell);
        }

        return numberSources;
    }

    public ArrayList<Integer> foodStack(String currentLine){

        String[] tabFoodStack;
        ArrayList<Integer>  listFoodStack = new ArrayList<Integer>();
        tabFoodStack = currentLine.split(" ");
        for (int i = 0; i < tabFoodStack.length ; i++){
            listFoodStack.add(i,Integer.parseInt(tabFoodStack[i]));
        }

        return listFoodStack;
    }

    public boolean checkMap (){
        int countSource = 0;
        int countAnthill = 0;
        boolean checkWall = true;

        for (int i = 0; i < this.sizeX; i++ ){
            for (int j = 0; j < this.sizeY; j++){

                Position actualPosition = new Position (i,j);
                Cell actualCell = getCellPosition(actualPosition);

                if ((i == 0)||(i == this.sizeX-1)||(j == 0)||(j == this.sizeY-1)){

                    if (!actualCell.isObstacle()){

                        checkWall = false;
                    }
                }
                else if (actualCell.isSource()){
                    countSource++;
                }
                else if (actualCell.isAntHill()){
                    countAnthill++;
                }

            }
        }
        if ((countAnthill == 1) && (countSource >= 1) && checkWall){
            return true;
        }

        return false;
    }
    public boolean getSources (){

        for (int i = 0; i< sizeX; i++){
            for (int j = 0; j< sizeY; j++){

                Position actualPosition = new Position(i,j);
                Cell actualCell = this.getCellPosition(actualPosition);
                if (actualCell.isSource()){
                    return checkPass(actualCell);
                }
            }
        }

        return false;
    }
    /*
    public boolean checkPass (Cell cellSource){

        ArrayList<Cell> cellCheck = new ArrayList<Cell>();
        int posX = cellSource.getPosition().getX();
        int posY = cellSource.getPosition().getY();

        for (int i = posX - 1; i <= posX + 1; i++){
            for (int j = posY - 1; j <= posY + 1; j++){

                Position actualPosition = new Position(i,j);
                Cell actualCell = this.getCellPosition(actualPosition);

                if (actualCell.isAntHill()){

                    return true;
                }

                else if (!actualCell.isObstacle()){

                    cellCheck.add(actualCell);

                }
            }
        }

        for (int k = 0; k < cellCheck.size(); k++){

            if(checkPass(cellCheck.get(k))){

                return true;
            }
        }

        return false;
    }
    */

    public boolean checkPass (Position positionSource){

        return false;
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
