package model.agents.behaviour;

import model.Position;
import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.elements.Element;
import model.elements.ElementType;
import model.elements.Pheromone;
import model.elements.Source;
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

        //System.out.println(ant + ": I'm fetching!");

        ArrayList<Cell> movableCell = ant.getMoveCells();
        ArrayList<Cell> path = ant.getPath();



        ArrayList<Double> probabilityArray = new ArrayList<Double>();
        Cell lastCell = new Cell();
        Cell actualCell =  agent.getMap().getCellPosition(agent.getPosition());//Cell which contains the ant

        if (actualCell.isAntHill()){
           // System.out.println("I'm the antHill");
            lastCell = actualCell;
            path.add(lastCell);
        }
        else {
            lastCell = path.get(path.size() - 1);
        }

        for (int i=0; i< path.size(); i++){
            if (movableCell.contains(path.get(i))){
                movableCell.remove(path.get(i));
            }
        }


        Cell nextCell = agent.getMap().nextCell(lastCell,actualCell); //Cell in front of the ant
        Cell endCell = new Cell();

        Boolean source = false;
        for (int i = 0 ; i < movableCell.size(); i++){
            if (movableCell.get(i).isSource()){
                Source actualSource = (Source)(movableCell.get(i).getElements().get(ElementType.fromString("Source")).get(0));
                if (actualSource.isUsable()){
                    endCell = movableCell.get(i);
                    source = true;
                }
            }
            //System.out.println("Cellule "+ i + ": "+movableCell.get(i).getPosition().getX()+" ; "+movableCell.get(i).getPosition().getY());
        }
        if (source == true){
            ant.getPath().add(endCell);
            ant.move(actualCell, endCell);
            ant.setBehaviour(new TakeFood());
           // System.out.println("Takefood");
        }
        else {
            //Choose a new cell


            if (movableCell.isEmpty()) {
                /*System.out.println("tableau vide");
                System.out.println("Prochaine cellule : "+lastCell.getPosition().getX()+" ; "+lastCell.getPosition().getY());
                ant.move(actualCell, lastCell);
                ant.getPath().remove(lastCell);*/
                //If there is no possibility to move , go back

                agent.setBehaviour(new Return());
            } else {

                // Probability for each possibleCell
                for (int i = 0; i < movableCell.size(); i++) {
                    double probability;
                    int distance;


                    Cell cellCompare = movableCell.get(i);


                    Position positionCompare = cellCompare.getPosition();


                    if (nextCell.getPosition().isEqual(positionCompare)) {
                        probability = 10; //orientation
                        if (cellCompare.hasPheromone()) {
                            Element pheromoneElement = cellCompare.getPheromone();
                            Pheromone pheromone = (Pheromone) pheromoneElement;
                            probability += pheromone.getQuantity(); //pheromone

                        }
                    }
                    else {
                        distance = nextCell.distance(cellCompare);
                        probability = 10 / (distance + 1); //orientation
                        if (cellCompare.hasPheromone()) {
                            Element pheromoneElement = cellCompare.getPheromone();
                            Pheromone pheromone = (Pheromone) pheromoneElement;
                            probability += pheromone.getQuantity(); //pheromone

                        }

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
                int i = 0;
                boolean newCell = true;

                while ((i < probabilityArray.size())&& newCell) {

                    cumulSum += probabilityArray.get(i) / sumProbability;

                    if (testProbability <= cumulSum) {
                        endCell = movableCell.get(i);
                        newCell = false;
                    }
                    i++;
                }
                //System.out.println("Prochaine cellule : "+endCell.getPosition().getX()+" ; "+endCell.getPosition().getY());

                ant.getPath().add(endCell);
                //System.out.println("cellule ajoutée deplace toi !");
                ant.move(actualCell, endCell);

            }
        }

    }
}
