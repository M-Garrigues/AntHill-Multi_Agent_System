package model.map;

import model.Position;
import model.elements.ElementType;

import java.util.Map;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {



    private java.util.Map<ElementType, ElementList> elements;
    private Position position;


    public Cell() {
    }

    public Cell(Position position) {
        this.position = position;
        initialize(elements);
    }

    private static void initialize(java.util.Map<ElementType, ElementList> elements){
        for(ElementType type : ElementType.values()){
            elements.put(type, new ElementList());
        }

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
