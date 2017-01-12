package model.map;

import model.elements.Element;

import java.util.ArrayList;
import java.util.Iterator;

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

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public Element get(int i){
        return elements.get(i);
    }




    public Iterator<Element> iterator(){
        return elements.iterator();
    }

    public boolean add(Element element){
        return(elements.add(element));
    }

    public boolean remove(Element element){
        return(elements.remove(element));
    }



    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
