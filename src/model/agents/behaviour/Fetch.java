package model.agents.behaviour;

import model.Position;
import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.elements.Element;
import model.elements.Pheromone;
import model.map.Cell;
import view.ErrorView;

import java.util.ArrayList;

/**
 * Created by Florian on 27/01/2017.
 */
public class Fetch implements Behaviour {

    public void act (Agent agent){

        Ant ant = new Ant();

        try{
            ant = (Ant)agent;
        }
        catch (Exception e){
            ErrorView.textError("Returning agent is not an Ant.");
        }

        System.out.println("I'm fetching!");

        ArrayList<Cell> movableCell = ant.getMoveCells();
        ArrayList<Cell> path = ant.getPath();



        ArrayList<Double> probabilityArray = new ArrayList<Double>();
        Cell lastCell = new Cell();
        Cell actualCell =  agent.getMap().getCellPosition(agent.getPosition());//Cell who contain the ant

        if (actualCell.isAntHill()){
            System.out.println("I'm the antHill");
            lastCell = actualCell;
        }
        else {
            lastCell = path.get(path.size() - 1);
        }


        Cell nextCell = agent.getMap().nextCell(lastCell,actualCell); //Cell in front of the ant
        Cell endCell = new Cell();

        Boolean source = false;
        for (int i = 0 ; i < movableCell.size(); i++){
            if (movableCell.get(i).isSource()){
                source = true;
                endCell = movableCell.get(i);
            }
            System.out.println("Cellule 1:"+movableCell.get(i).getPosition().getX()+" : "+movableCell.get(i).getPosition().getY());
        }
        if (source == true){
            ant.getPath().add(endCell);
            ant.move(actualCell, endCell);
            ant.setBehaviour(new TakeFood());
            System.out.println("Takefood");
        }
        else {
            //Choose a new cell

            /*if (movableCell.contains(lastCell)){
                movableCell.remove(lastCell);
            }*/

            System.out.println("ifelse");

            if (movableCell.isEmpty()) {
                System.out.println("tableau vide");
                ant.getPath().add(lastCell);
                ant.move(actualCell, lastCell);
                //If there is no possibility to move , go back
            } else {

                System.out.println("ici");
                System.out.println(movableCell.size());
                // Probability for each possibleCell
                for (int i = 0; i < movableCell.size(); i++) {
                    System.out.println("la");
                    double probability;
                    int distance;

                    Cell cellCompare = movableCell.get(i);

                    Position positionCompare = cellCompare.getPosition();
                    Element pheromoneElement = cellCompare.getPheromone();

                    Pheromone pheromone = (Pheromone) pheromoneElement;

                    if (nextCell.getPosition().isEqual(positionCompare)) {
                        System.out.println("lalalalala");
                        probability = 10; //orientation
                        probability += pheromone.getQuantity(); //pheromone

                    } else {
                        System.out.println("xaxaxaxaxa");
                        distance = nextCell.distance(cellCompare);
                        System.out.println("Distance :"+distance);
                        probability = 10 / (distance + 1); //orientation
                        System.out.println("Pheromone :"+pheromone.getQuantity());
                        probability += pheromone.getQuantity(); //pheromone
                    }

                    probabilityArray.add(probability);
                    System.out.println("proba ? : "+probability);
                }

                //Sum for all probability
                double sumProbability = 0;

                for (int i = 0; i < probabilityArray.size(); i++) {

                    System.out.println("Proba cellule : "+i+" : "+probabilityArray.get(i));

                    sumProbability += probabilityArray.get(i);
                }

                //Generating random number to chose next cell
                double testProbability = Math.random();
                double cumulSum = 0;

                for (int i = 0; i < probabilityArray.size(); i++) {

                    cumulSum += probabilityArray.get(i) / sumProbability;

                    if (testProbability <= cumulSum) {

                        endCell = movableCell.get(i);
                    }
                }

                ant.getPath().add(endCell);
                ant.move(actualCell, endCell);
            }
        }

    }
}
