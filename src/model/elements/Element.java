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




    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}


