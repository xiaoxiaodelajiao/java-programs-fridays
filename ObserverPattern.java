import java.util.HashSet;
import java.util.Iterator;

abstract class Observer{
    
    abstract void notify(Subject subject);
}

class Subject{
    HashSet <Observer> observers = new HashSet<Observer>();
    
    void add(Observer observer){
        observers.add(observer);
    }
    
    void remove(Observer observer){
        observers.remove(observer);
    }
    
    void notifyObservers(){
        Iterator<Observer> i = observers.iterator();
        while (i.hasNext()){
            i.next().notify();
        }
    }
}

class ObserverPattern{
    public static void main(String[]args){

    }
}

