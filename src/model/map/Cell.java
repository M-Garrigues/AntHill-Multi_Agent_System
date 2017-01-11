package model.map;

import model.Position;
import model.elements.Element;

import java.util.ArrayList;
import java.util.Iterator;

import static view.ErrorView.textError;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {

    private ArrayList<Element> elements;
    private Position position;



    public Cell() {
    }

    public Cell(Position position){
        this.position = position;
        elements = new ArrayList<Element>();
    }

    public Cell(ArrayList<Element> elements) {
        this.elements = new ArrayList<Element>();
    }


    public void addElement (Element element){
        elements.add(element);
    }

    public void deleteElement(Element element){
        try{
            elements.remove(element);
        }
        catch (Exception e){
            textError("Tried to remove a non-existent element from a cell.");
        }
    }




    public boolean isObstacle(){
        return containsElement("Obstacle");
    }

    public boolean isAntHill(){
        return containsElement("AntHill");
    }

    public boolean isSource(){
        return containsElement("Source");
    }

    public boolean hasPheromone(){
        return containsElement("Pheromone");
    }

    public boolean hasAnt(){
        return containsElement("Ant");
    }


    private boolean containsElement(String name){
        boolean test = false;
        for(Iterator<Element> i = elements.iterator(); i.hasNext();){
            if(i.next().getClass().getName() == name) test = true;
        }
        if (test) return true;
        else return false;
    }


    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
