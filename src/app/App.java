package app;

import model.Position;
import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.agents.mobileAgent.movement.OneStep;
import model.agents.vision.Godlike;
import model.map.Map;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mathieu on 10/01/2017.
 */

public class App {
    public static void main(String[] args)
    {
        boolean test;
        Map map = new Map(2,2);
        map.loadMap();
        System.out.println(Runtime.getRuntime().availableProcessors());


        System.out.println(Thread.currentThread().getName());

        ArrayList<Agent> ants = createAnts(10);

        for (Agent ant: ants) {
            map.getCellPosition(ant.getPosition()).addElement(ant); //adds the ant to the cell it belongs.
        }

        for(int i = 0; i < 10; i++){

            System.out.println("\n\n ===== Boucle "+ i +" ===== \n\n");

            ExecutorService executor = Executors.newCachedThreadPool();

            ants.forEach(executor::submit);

            executor.shutdown();

            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            }
            catch (Exception e) {
            }
            finally {

            }

        }
        System.out.println("tout fini lol");





        //map.printMap();


        //Main test map + settings
        /*Settings set = new Settings();
        Map newMap = new Map(2,2);
        set.readFile();
        newMap.genMap(set);
        newMap.printMap();*/

        //Main test addElement
        /*Position position = new Position(1,1);
        Source source = new Source(position,true,20);
        Cell cell = new Cell (position);
        cell.addElement(source);
        cell.printCell();*/




        /*int randomnum = 1 + (int)(Math.random() * 8);
        System.out.println(randomnum);
       /* Settings settings = new Settings();

        System.out.printf("hello anthill");


        settings.readFile();*/
/*

        Map map = new Map(10,10);
        map.genMap(map);
        map.printMap(map);


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
*/



    }



    public static ArrayList<Agent> createAnts(int nb){

        ArrayList<Agent> ants = new ArrayList<Agent>();

        for(int i = 0; i < nb; i++){
            ants.add(new Ant(new Position(i,i), new OneStep(), new Godlike()));
        }
        return ants;
    }
}
