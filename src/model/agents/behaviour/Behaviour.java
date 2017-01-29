package model.agents.behaviour;

import model.agents.Agent;

/**
 * Created by Florian on 27/01/2017.
 */
public interface Behaviour {

    /* The behaviour is what an agent will do when called.
       It will have previously observed its environment, and eventually the places it could go to.
       Some behaviours are exclusive for some subclasses (For example the MobileAgent subclasses for the behaviours that require to move).
     */


    public void act(Agent agent);
}
