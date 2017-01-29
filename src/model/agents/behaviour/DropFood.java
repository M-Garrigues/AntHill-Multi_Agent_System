package model.agents.behaviour;

import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.elements.AntHill;
import model.elements.Element;
import model.elements.ElementType;
import model.map.Cell;
import view.ErrorView;

/**
 * Created by Florian on 28/01/2017.
 */
public class DropFood implements Behaviour {

    public void act(Agent agent){

        Ant ant = new Ant();
        Cell actualCell = agent.getMap().getCellPosition(agent.getPosition());


        try{
            ant = (Ant)agent;
        }
        catch (Exception e){
            ErrorView.textError("Can't take food as this agent doesn't carry any.");
        }

        if(ant.hasFood())
            System.out.println(ant + ": I'm droping food!");
        else{
            System.out.println(ant + ": I am home, let's fetch again!");
        }

        if (actualCell.isAntHill() && ant.hasFood()){
            ant.dropFood();
            Element antHillElement = actualCell.getElements().get(ElementType.AntHill).get(0);
            AntHill antHill = (AntHill) antHillElement;
            antHill.getFood();
        }

        ant.setBehaviour(new Fetch());

    }
}
