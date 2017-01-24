package model.elements;

import model.Position;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Element {

    private Position position;


    public Element(){

    }


    public Element(Position position) {
        this.position = position;
    }




    public synchronized Position getPosition() {
        return position;
    }

    public synchronized void setPosition(Position position) {
        this.position = position;
    }
}


