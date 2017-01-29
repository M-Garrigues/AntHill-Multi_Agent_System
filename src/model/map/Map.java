package model.map;

import model.Position;
import model.elements.AntHill;
import model.elements.ElementType;
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

    private Position positionAntHill;
    private int food;



    private int sizeX;
    private int sizeY;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.food = 0;
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
    public boolean getSources (){

        for (int i = 0; i< sizeX; i++){
            for (int j = 0; j< sizeY; j++){

                ArrayList<Cell> cellChecked = new ArrayList<Cell>();
                int counter = cellChecked.size();
                Position actualPosition = new Position(i,j);
                Cell actualCell = this.getCellPosition(actualPosition);
                if (actualCell.isSource()){
                    return checkPass(actualCell,cellChecked,counter);
                }
            }
        }

        return false;
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

    public boolean checkPass (Cell cellSource, ArrayList<Cell> cellChecked,int sizeArray){

        ArrayList<Cell> cellCheck = new ArrayList<Cell>();
        Position cellPosition = cellSource.getPosition();
        int posX = cellPosition.getX();
        int posY = cellPosition.getY();

        for (int i = posX - 1 ; i < posX + 1 ; i++ ){
            for (int j = posY - 1 ; j < posY + 1 ; j++){

                Position actualPosition = new Position (i,j);
                Cell actualCell = new Cell(actualPosition);
                boolean obstacle = actualCell.isObstacle();
                boolean checked = cellChecked.contains(actualCell);

                if (actualCell.isAntHill()){
                    System.out.println("passe ici");
                    //Fourmilliere trouvée = arret
                    return true;
                }

                else if (!obstacle && !checked){
                    cellCheck.add(actualCell); //Array with cells to check
                    cellChecked.add(actualCell); //Array with cells already checked
                }
            }
        }
        if (cellChecked.size() == sizeArray){
            //Aucune cellule rajoutable = arret
            return false;
        }
        else {
            for (int k = 0; k < cellCheck.size(); k++){
                boolean testMap = checkPass(cellCheck.get(k),cellChecked,cellChecked.size());
                System.out.println("ici");
                return testMap;
            }
        }

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
