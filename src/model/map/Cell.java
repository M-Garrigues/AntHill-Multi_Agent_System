package model.map;

import model.Position;
import model.elements.Element;
import model.elements.ElementType;

import java.util.EnumMap;
import java.util.Map;

import static view.ErrorView.textError;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {



    private java.util.Map<ElementType, ElementList> elements;
    private Position position;


    public Cell() {
        position = new Position(0,0);
        elements = new EnumMap<ElementType, ElementList>;
        initialize(elements);
    }

    public Cell(Position position) {
        this.position = position;
        elements = new EnumMap<ElementType, ElementList>;
        initialize(elements);
    }

    private static void initialize(java.util.Map<ElementType, ElementList> elements) {
        for (ElementType type : ElementType.values()) {
            elements.put(type, new ElementList());
        }
    }

    public void addElement(Element element){
        String className = new String (element.getClass().getName());

        className = className.substring(className.lastIndexOf(".") + 1);

        elements.get(className).add(element);
    }





    public boolean isObstacle(){
        return(!elements.get("Obstacle").isEmpty());
    }

    public boolean isSource(){
        return(!elements.get("Source").isEmpty());
    }

    public boolean isAntHill(){
        return(!elements.get("AntHill").isEmpty());
    }

    public boolean hasPheromone(){
        return(!elements.get("Pheromone").isEmpty());
    }



    public Element getPheromone(){
        try{
            return elements.get("Pheromone").get(0);
        }
        catch(Exception e){
            textError("get pheromone on empty cell.");
        }
        return null;
    }






    public java.util.Map<ElementType, ElementList> getElements() {
        return elements;
    }

    public void setElements(Map<ElementType, ElementList> elements) {
        this.elements = elements;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



}
