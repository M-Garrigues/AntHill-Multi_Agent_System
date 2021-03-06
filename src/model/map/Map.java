package model.map;

import model.Position;
import model.agents.Agent;
import model.elements.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Map {

    private ArrayList<Cell> cells;

    private Position positionAntHill;
    private int food;



    private int sizeX;
    private int sizeY;

    public Map(int sizeX, int sizeY) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.food = 0;
        this.cells = new ArrayList<Cell>();
    }

    public void loadMap (){

        String filename = "data/map/map.txt";
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

                    positionAntHill = actualPosition;

                    break;

                case 'o' :
                    int foodStack = listFoodStack.get(numberSources - 1);
                    Source source = new Source(actualPosition,true,foodStack);
                    actualCell.addElement(source);
                    numberSources--;
                    this.food += foodStack;
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
    public int[] getSources (){

        int[] counter = new int[2];
        counter[0] = 0;
        counter[1] = 0;
        for (int i = 0; i< sizeX; i++){
            for (int j = 0; j< sizeY; j++){
                ArrayList<Cell> cellChecked = new ArrayList<Cell>();
                Position actualPosition = new Position(i,j);
                Cell actualCell = this.getCellPosition(actualPosition);
                if (actualCell.isSource()){
                    counter[1]++;
                    Source source = (Source)(actualCell.getElements().get(ElementType.fromString("Source")).get(0));
                    if (source.getFastway() != 0){
                        System.out.println("Source fastest way :"+source.getFastway());
                        counter[0]++;
                    }
                }
            }
        }

        return counter;
    }

    public void reducePheromone (){
        for (int i = 0; i < this.getSizeX(); i++){
            for (int j = 0; j < this.getSizeY(); j++){
                Position actualPosition = new Position(i,j);
                Cell actualCell = this.getCellPosition(actualPosition);
                if (actualCell.hasPheromone()){
                    int quantityPheromone = ((Pheromone)actualCell.getPheromone()).getQuantity();
                    if (quantityPheromone != 0){
                        //actualCell.deleteElement((Pheromone)actualCell.getPheromone());
                        ((Pheromone)actualCell.getPheromone()).setQuantity(quantityPheromone-1);
                    }
                }
            }
        }
    }

    public Cell nextCell (Cell lastCell, Cell actualCell){

        int posX = actualCell.getPosition().getX();
        int posY = actualCell.getPosition().getY();

        int lastX = lastCell.getPosition().getX();
        int lastY = lastCell.getPosition().getY();

        int deltaX = posX - lastX;
        int deltaY = posY - lastY;

        int nextX = posX + deltaX;
        int nextY = posY + deltaY;

        Position nextPosition = new Position(nextX,nextY);
        Cell nextCell = this.getCellPosition(nextPosition);

        return nextCell;
    }

    public Cell getCellPosition (Position position){
        int posX = position.getX();
        int posY = position.getY();
        int index = posY*this.sizeX+ posX;
        return this.cells.get(index);
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

    public Position getPositionAntHill() {
        return positionAntHill;
    }

    public void setPositionAntHill(Position positionAntHill) {
        this.positionAntHill = positionAntHill;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public AntHill getAntHill(){
        AntHill anthill;

        anthill = (AntHill)this.getCellPosition(this.positionAntHill).getElements().get(ElementType.fromString("AntHill")).get(0);

        return anthill;
    }
}
