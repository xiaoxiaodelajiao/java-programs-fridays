import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

class Point{
    int time;
    int pos;
    
    Point(int time, int pos){
        this.time = time;
        this.pos = pos;
    }
    
    int getTime(){
        return time;
    }
}
public class MaxSpeed{

    public static double maxSpeed(ArrayList<Point> bae){
        bae.sort(Comparator.comparing(Point::getTime));
        double fakeMax;
        double max = Math.abs((bae.get(1).pos-bae.get(0).pos+0.0)/(bae.get(1).time-bae.get(0).time+0.0));
        for(int i = 1; i < bae.size()-1; i ++){
            fakeMax = Math.abs((bae.get(i+1).pos-bae.get(i).pos+0.0)/(bae.get(i+1).time-bae.get(i).time+0.0));
            if(max < fakeMax) max = fakeMax;
        }
        
        return max;
        
    }

    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        int arrayLength = input.nextInt();
        ArrayList<Point> bae = new ArrayList<Point>(arrayLength);
        for(int i = 0; i < arrayLength; i ++){
            bae.add(new Point(input.nextInt(), input.nextInt()));
        }
        System.out.println(maxSpeed(bae));
    }
}