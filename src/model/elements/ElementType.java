package model.elements;

/**
 * Coded by Mathieu GARRIGUES on 12/01/2017.
 */
public enum ElementType {

    AntHill,
    Obstacle,
    Pheromone,
    Source,
    Ant;

public static ElementType fromString(String text){
    return valueOf(text);
}

public static String getClassName(Element element){
    String className;
    className = new String (element.getClass().getName());
    className = className.substring(className.lastIndexOf(".") + 1);
    return className;
}


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
