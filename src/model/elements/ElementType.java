package model.elements;

/**
 * Coded by Mathieu GARRIGUES on 12/01/2017.
 */
public enum ElementType {

    AntHill,
    Obstacle,
    Pheromone,
    Source;
    //Ant;



    /*public static ElementType fromString(String text) {
        if (text != null) {
            for (ElementType b : ElementType.values()) {
                if (b.valueOf()substring(b.lastIndexOf(".") + 1)) {
                    return b;
                }
            }
        }
        return null;
    }*/
}
