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
                }
                counter ++;
            }
            System.out.println("Taille X : "+ this.sizeX);
            System.out.println("Taille Y : "+ this.sizeY);
            System.out.println("Nombre de sources "+ numberSources);
            for (int i = 0; i< listFoodStack.size(); i++){
                System.out.println("Source : "+ i +" : "+ listFoodStack.get(i));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

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
