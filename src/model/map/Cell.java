package model.map;

import model.Position;
import model.elements.Element;
import model.elements.ElementType;

import java.util.EnumMap;

import static view.ErrorView.textError;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {



    private EnumMap<ElementType, ElementList> elements;
    private Position position;


    public Cell() {
        position = new Position(0,0);
        elements = new EnumMap<>(ElementType.class);
        initialize(elements);
    }

    public Cell(Position position) {
        this.position = position;
        elements = new EnumMap<>(ElementType.class);
        initialize(elements);
    }

    private static void initialize(EnumMap<ElementType, ElementList> elements) {
        for (ElementType type : ElementType.values()) {
            elements.put(type, new ElementList());
        }
    }

    public void addElement(Element element){

        /* This function gets the element's class name, and uses it to find is associated enum (valueOf(className) ).
        Once done, it adds the element to its specified ElementList.*/


        String className;
        className = new String (element.getClass().getName());

        className = className.substring(className.lastIndexOf(".") + 1);

        ElementType keyElement = ElementType.valueOf(className);
        this.elements.get(keyElement).add(element);
    }

    public boolean isEmpty (){
        if (this.elements.isEmpty()){
            return true;
        }
        else return false;
    }




    public boolean isObstacle(){
        return(!elements.get(ElementType.Obstacle).isEmpty());
    }

    public boolean isSource(){
        return(!elements.get(ElementType.Source).isEmpty());
    }

    public boolean isAntHill(){
        return(!elements.get(ElementType.AntHill).isEmpty());
    }

    public boolean hasPheromone(){
        return(!elements.get(ElementType.Pheromone).isEmpty());
    }



    public Element getPheromone(){
        try{
            return elements.get(ElementType.Pheromone).get(0);
        }
        catch(Exception e){
            textError("get pheromone on empty cell.");
        }
        return null;
    }






    public EnumMap<ElementType, ElementList> getElements() {
        return elements;
    }

    public void setElements(EnumMap<ElementType, ElementList> elements) {
        this.elements = elements;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



}
