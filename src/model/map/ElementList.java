package model.map;

import model.elements.Element;

import java.util.ArrayList;

/**
 * Coded by Mathieu GARRIGUES on 11/01/2017.
 */
public class ElementList {
    private ArrayList<Element> elements;

    public ElementList(){
        this.elements = new ArrayList<Element>();
    }

    public ElementList(ArrayList<Element> elements) {
        this.elements = elements;
    }



    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
