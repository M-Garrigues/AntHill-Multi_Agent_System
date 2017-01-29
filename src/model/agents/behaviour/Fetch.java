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


        if (movableCell.isEmpty()) {
            System.out.println("Pas de déplacement possible!");
        }
        else{
            for (Cell move: movableCell
                    ) {
                System.out.println(move.getPosition().getX()+";"+move.getPosition().getY());
            }
        }


        ArrayList<Double> probabilityArray = new ArrayList<Double>();

        Cell lastCell = path.get(path.size() - 1);
        Cell actualCell =  agent.getMap().getCellPosition(agent.getPosition());//Cell who contain the ant
        Cell nextCell = agent.getMap().nextCell(lastCell,actualCell); //Cell in front of the ant
        Cell endCell = new Cell();

        Boolean source = false;
        for (int i = 0 ; i < movableCell.size(); i++){
            if (movableCell.get(i).isSource()){
                source = true;
                endCell = movableCell.get(i);
            }
        }
        if (source == true){
            ant.getPath().add(endCell);
            ant.move(actualCell, endCell);
            //Change behaviour to getFood
        }
        else {

            //Choose a new cell

            for (int i = 0; i < path.size(); i++) {
                if (movableCell.contains(path.get(i))) {
                    movableCell.remove(path.get(i));
                    //Ant don't come back on her path
                }
            }
            if (movableCell.isEmpty()) {

                ant.getPath().add(lastCell);
                ant.move(actualCell, lastCell);
                //If there is no possibility to move , go back
            } else {

                // Probability for each possibleCell
                for (int i = 0; i < movableCell.size(); i++) {

                    double probability;
                    int distance;

                    Cell cellCompare = movableCell.get(i);

                    Position positionCompare = cellCompare.getPosition();
                    Element pheromoneElement = cellCompare.getPheromone();

                    Pheromone pheromone = (Pheromone) pheromoneElement;

                    if (nextCell.getPosition().isEqual(positionCompare)) {
                        probability = 10; //orientation
                        probability += pheromone.getQuantity(); //pheromone

                    } else {
                        distance = nextCell.distance(cellCompare);
                        probability = 10 / (distance + 1); //orientation
                        probability += pheromone.getQuantity(); //pheromone
                    }

                    probabilityArray.add(probability);
                }

                //Sum for all probability
                double sumProbability = 0;

                for (int i = 0; i < probabilityArray.size(); i++) {

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
