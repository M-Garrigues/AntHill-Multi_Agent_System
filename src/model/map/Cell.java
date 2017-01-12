package model.map;

import model.Position;
import model.elements.Element;
import model.elements.ElementType;

import java.util.Map;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {



    private java.util.Map<ElementType, ElementList> elements;
    private Position position;


    public Cell() {
        position = new Position(0,0);
        initialize(elements);
    }

    public Cell(Position position) {
        this.position = position;
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
