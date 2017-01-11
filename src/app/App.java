package app;

import model.Position;
import model.elements.Source;
import model.map.Cell;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class App {
    public static void main(String[] args)
    {

        System.out.printf("hello anthill");

        Position pos = new Position(0,1);
        Cell cell = new Cell(pos);

        Source source = new Source(pos, true, 20);

        System.out.println(source.getFoodStack());
        source.takeFood();
        System.out.println(source.getFoodStack());


        cell.addElement(source);

    }
}
