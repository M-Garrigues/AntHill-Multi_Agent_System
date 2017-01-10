package model;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Position{

    private int x;
    private int y;


    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public boolean isEqual(Position pos)
    {
        if(this.y == pos.getY() && this.x == pos.getX())
            return true;
        else
            return false;
    }




    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}