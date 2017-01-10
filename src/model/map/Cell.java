package model.map;

import model.elements.Element;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {

    private List<Element> elements;

    public Cell() {
    }

    public Cell(List<Element> elements) {
        this.elements = elements;
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


    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
