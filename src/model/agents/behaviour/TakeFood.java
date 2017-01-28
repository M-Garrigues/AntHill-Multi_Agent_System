package model.agents.behaviour;

import model.agents.Agent;
import model.agents.mobileAgent.ant.Ant;
import model.elements.ElementType;
import model.elements.Source;
import model.map.Cell;
import view.ErrorView;

/**
 * Created by Mathieu on 28/01/2017.
 */
public class TakeFood implements Behaviour{

    public TakeFood(){}


    public void act(Agent agent){

        Ant ant = new Ant();
        Cell actualCell = agent.getMap().getCellPosition(agent.getPosition());


        try{
            ant = (Ant)agent;
        }
        catch (Exception e){
            ErrorView.textError("Can't take food as this agent doesn't carry any.");
        }
        finally {
        }



        if(actualCell.isSource()){
            if(((Source)(actualCell.getElements().get(ElementType.fromString("Source")).get(0))).isUsable()){

                ((Source)(actualCell.getElements().get(ElementType.fromString("Source")).get(0))).foodTaken();

                ant.takeFood();

                ant.setBehaviour(new Return()); // The agent has found food, it comes back.

            }
            else {
                ant.setBehaviour(new Fetch()); // The agent can't take food on this source, he continues the fetching.
            }
        }


    }


}
