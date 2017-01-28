package model.map;

import model.Position;
import model.elements.Element;
import model.elements.ElementType;
import model.elements.Pheromone;

import java.util.EnumMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static view.ErrorView.textError;

/**
 * Created by Mathieu on 10/01/2017.
 */
public class Cell {



    private EnumMap<ElementType, ElementList> elements;
    private Position position;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();


    public Cell() {
        position = new Position(0,0);
        elements = new EnumMap<>(ElementType.class);
        initialize(elements);
    }

    public Cell(Position position) {
        this.position = position;
        elements = new EnumMap<>(ElementType.class);
        initialize(elements);
    }

    private static void initialize(EnumMap<ElementType, ElementList> elements) {
        for (ElementType type : ElementType.values()) {
            elements.put(type, new ElementList());
        }
    }





    //===================================== WRITE LOCK USED ========================================

    public void lock(){
        this.writeLock.lock();
    }

    public void unlock(){
        this.writeLock.unlock();
    }





    public void addElement(Element element){

        writeLock.lock();

        try{

            /* This function gets the element's class name, and uses it to find is associated enum (valueOf(className) ).
            Once done, it adds the element to its specified ElementList.*/
            String className;
            className = ElementType.getClassName(element);
            ElementType keyElement = ElementType.fromString(className);
            this.elements.get(keyElement).add(element);
        }
        finally {
            writeLock.unlock();
        }
    }


    public void deleteElement (Element element){

        writeLock.lock();

        try{
            String className = ElementType.getClassName(element);

            ElementType keyElement = ElementType.fromString(className);

            this.elements.get(keyElement).remove(element);
        }
        finally {
            writeLock.unlock();
        }
    }


    public void setElements(EnumMap<ElementType, ElementList> elements) {

        writeLock.lock();

        try{
            this.elements = elements;
        }
        finally {
            writeLock.unlock();
        }
    }



    public void setPosition(Position position) {

        writeLock.lock();

        try{
            this.position = position;
        }
        finally {
            writeLock.unlock();
        }
    }

    public void setPheromone(int value) {

        writeLock.lock();

        try{
            ((Pheromone)this.elements.get(ElementType.Pheromone).get(0)).setQuantity(value);
        }
        finally {
            writeLock.unlock();
        }
    }





    //===================================== READ LOCK USED ========================================




    public boolean cellEmpty(){

        readLock.lock();

        try{
            if ((this.elements.get(ElementType.AntHill).isEmpty()) && (this.elements.get(ElementType.Obstacle).isEmpty()) && (this.elements.get(ElementType.Obstacle).isEmpty())){
                return true;
            }
            else{
                return false;
            }
        }
        finally {
            readLock.unlock();
        }

    }


    public boolean isObstacle(){

        readLock.lock();
        boolean b = true;

        try{
            b = !elements.get(ElementType.Obstacle).isEmpty();
        }
        catch (Exception e){
            b = true;
            textError("Error on obstacle test.");
        }
        finally {
            readLock.unlock();
            return b;

        }
    }

    public boolean isSource(){
        readLock.lock();
        boolean b = true;

        try{
            b = !elements.get(ElementType.Source).isEmpty();
        }
        catch (Exception e){
            b = true;
            textError("Error on source test.");
        }
        finally {
            readLock.unlock();
            return b;
        }

    }

    public boolean isAntHill(){

        readLock.lock();
        boolean b = true;

        try{
            b = !elements.get(ElementType.AntHill).isEmpty();
        }
        catch (Exception e){
            b = true;
            textError("Error on antHill test.");
        }
        finally {
            readLock.unlock();
            return b;

        }

    }

    public boolean hasPheromone(){

        readLock.lock();
        boolean b = true;

        try{
            b = !elements.get(ElementType.Pheromone).isEmpty();
        }
        catch (Exception e){
            b = true;
            textError("Error on pheromone test.");
        }
        finally {
            readLock.unlock();
            return b;

        }
    }

    public boolean hasAnt(){

        readLock.lock();
        boolean b = true;

        try{
            b = !elements.get(ElementType.Ant).isEmpty();
        }
        catch (Exception e){
            b = true;
            textError("Error on ant test.");
        }
        finally {
            readLock.unlock();
            return b;

        }
    }


    public Element getPheromone(){

        readLock.lock();

        try{
            return elements.get(ElementType.Pheromone).get(0);
        }
        catch(Exception e){
            textError("get pheromone on empty cell.");
        }
        finally {
            readLock.unlock();
        }
        return null;
    }



    public EnumMap<ElementType, ElementList> getElements() {

        readLock.lock();

        try{
            return elements;
        }
        catch(Exception e){
            textError("get elements failed.");
        }
        finally {
            readLock.unlock();
        }
        return null;
    }

    public int distance (Cell compareCell){

        int distance;
        int posX = this.getPosition().getX();
        int posY = this.getPosition().getY();
        int compX = compareCell.getPosition().getX();
        int compY = compareCell.getPosition().getY();

        distance = Math.abs(posX - compX) + Math.abs(posY - compY);

        return distance;
    }


    public Position getPosition() {

        readLock.lock();

        try{
            return position;
        }
        catch(Exception e){
            textError("get pheromone on empty cell.");
        }
        finally {
            readLock.unlock();
        }
        return null;
    }








    public void printCell (){

        readLock.lock();

        try{
            System.out.println("Position X : " + this.position.getX() + " Position Y : "+ this.position.getY());
            System.out.println("Anthill : " + !this.elements.get(ElementType.AntHill).isEmpty());
            System.out.println("Obstacle : " + !this.elements.get(ElementType.Obstacle).isEmpty());
            System.out.println("Source : " + !this.elements.get(ElementType.Source).isEmpty());
        }
        finally {
            readLock.unlock();
        }
    }
}
