public class ParentChildClass{
    public static void main(String []args){
        Elephant e = new Elephant("Harry", 2, 9);
        Tiger t = new Tiger("Johnny", 1);
        Fish nemo = new Fish("Nemo", 1);
        e.Report();
        nemo.Report();
    }
}

abstract class Animal{
    protected String name;
    int gender;
    
    Animal(String name, int gender){
        this.name = name;
        this.gender = gender;
    }
    
    void Report(){
        System.out.println("Reported");
    }
    
    abstract void Feed();
}

abstract class Mammal extends Animal{
    Mammal(String name, int gender){
        super(name, gender);
    }
    
    void Wash(){
        System.out.println("Gave " + name + " a bath");
    }
    
    void Report(){
        System.out.println("Reported mammal");
    }
}

class Elephant extends Mammal{
    int tuskLength;
    Elephant(String name, int gender, int tuskLength){
        super(name, gender);
        this.tuskLength = tuskLength;
    }
    
    void Feed(){
        System.out.println("Fed elephant apples");
    }
    
}

class Tiger extends Mammal{
    Tiger(String name, int gender){
        super(name, gender);
    }
    
    void Feed(){
        System.out.println("Fed tiger meat");
    }
}

class Fish extends Animal{
    Fish(String name, int gender){
        super(name, gender);
    }
    
    void Feed(){
        System.out.println("Fed fish seaweed");
    }
}