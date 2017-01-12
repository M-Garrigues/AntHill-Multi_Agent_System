package app;

import model.Position;
import model.elements.AntHill;
import model.elements.Obstacle;
import model.elements.Pheromone;
import model.elements.Source;
import model.map.Cell;
import model.map.Map;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class App {
    public static void main(String[] args)
    {
        Settings settings = new Settings();

        System.out.printf("hello anthill");


        Map map = new Map(10,10);
        //map.genMap(map);
        //map.printMap(map);


        Position pos = new Position(0,1);
        Position pos2 = new Position(1,1);
        Cell cell = new Cell(pos);
        Cell cell2 = new Cell(pos2);

        Source source = new Source(pos, true, 20);
        AntHill antHill = new AntHill(pos, settings.getNbAnts());
        Pheromone pheromone = new Pheromone(pos, 4);
        Obstacle obstacle = new Obstacle(pos2);


        System.out.println(source.getFoodStack());
        source.takeFood();
        System.out.println(source.getFoodStack());

        cell.addElement(source);
        cell.addElement(pheromone);
        cell.addElement(antHill);
        cell2.addElement(obstacle);


        if(cell.isSource())System.out.println("Est bien source");
        if(cell.isAntHill())System.out.println("Est bien fourmilliere");
        if(cell.hasPheromone())System.out.println("Est bien pheromone");
        if(cell2.isObstacle())System.out.println("Est bien obstacle");
        if(!cell2.isSource())System.out.println("n'Est pas source");
        if(!cell2.isAntHill())System.out.println("n'est pas fourmilliere");
        if(!cell2.hasPheromone())System.out.println("n'est pas pheromone");
        if(!cell.isObstacle())System.out.println("n'est pas obstacle");
        System.out.println(cell.getElements().getClass().getName());




    }
}
