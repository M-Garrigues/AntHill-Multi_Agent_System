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

            while ((i < path.size()) && newPath) {
                for (int j = 0; j < movableCells.size(); j++) {

                    Cell cellPath = path.get(i);
                    Cell cellMove = path.get(j);
                    if (cellPath.getPosition().isEqual(cellMove.getPosition())) {
                        endCell = cellPath;
                        newPath = false;
                    }
                }
                i++;
            }

            if(!startCell.hasPheromone()){
                Pheromone pheromone = new Pheromone(startCell.getPosition(), 10);
                startCell.addElement(pheromone);
            }
            else{
                int quantityPheromone = ((Pheromone)startCell.getPheromone()).getQuantity();
                startCell.setPheromone(quantityPheromone + 10);
            }

            ant.move(startCell, endCell);

            if(endCell.isAntHill()){  //The agent is on an anthill, his state goes to dropFood.
                agent.setBehaviour(new DropFood());
            }
        }
    }
}
