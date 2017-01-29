package model.agents.behaviour;

import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.elements.Pheromone;
import model.map.Cell;
import view.ErrorView;

import java.util.ArrayList;

/**
 * Created by Mathieu on 27/01/2017.
 */
public class Return implements Behaviour {

    public Return() {

    }

    public void act(Agent agent) {

        Ant ant = new Ant();

        try {
            ant = (Ant) agent;
        } catch (Exception e) {
            ErrorView.textError("Returning agent is not an Ant.");
        }

        //System.out.println("I'm returning home!");

        Cell startCell = agent.getMap().getCellPosition(agent.getPosition());
        Cell endCell = new Cell();

        if (startCell.isAntHill()) {
            ant.setBehaviour(new DropFood());
        }

        else {

            ArrayList<Cell> movableCells = ant.getMoveCells();
            ArrayList<Cell> path = ant.getPath();
            int i = 0;
            boolean newPath = true;
            /*
            while ((i < path.size()) && newPath) {
                System.out.println(newPath);
                for (int j = 0; j < movableCells.size(); j++) {

                    Cell cellPath = path.get(i);
                    Cell cellMove = path.get(j);
                    if (cellPath.getPosition().isEqual(cellMove.getPosition())) {
                        System.out.println("je passe ici");
                        endCell = cellPath;
                        newPath = false;
                    }
                }
                i++;
            }*/

            endCell= path.get(path.size()-1);
            ant.getPath().remove(path.size()-1);
            //System.out.println(startCell.hasPheromone());
            if(!startCell.hasPheromone() && ant.hasFood()){
               // System.out.println("Je pose des phéromones");
                Pheromone pheromone = new Pheromone(startCell.getPosition(), 100);
                startCell.addElement(pheromone);
            }
            else if(startCell.hasPheromone() && ant.hasFood()) {
                //System.out.println("Je pose des phéromones");
                int quantityPheromone = ((Pheromone)startCell.getPheromone()).getQuantity();
                startCell.setPheromone(quantityPheromone + 100);
            }
            else{
                //System.out.println("Je ne pose pas de phéromone parceque je suis un déchet");
            }

            //System.out.println("Prochaine cellule : "+endCell.getPosition().getX()+" ; "+endCell.getPosition().getY());
            ant.move(startCell, endCell);

            if(endCell.isAntHill()){  //The agent is on an anthill, his state goes to dropFood.
                agent.setBehaviour(new DropFood());
            }
        }
    }
}
