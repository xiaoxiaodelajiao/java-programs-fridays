import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class Point{
    int row;
    int col;
    
    Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main{

    public static boolean escapable(HashMap<Integer, ArrayList<Point>> room, Point position, boolean[][] hasChecked){
        if(position.row == 1 && position.col == 1) return true;
        if(hasChecked[position.row-1][position.col-1]) return false;
        hasChecked[position.row-1][position.col-1] = true;
        
        int value = position.row*position.col;
        if(!room.containsKey(value)) return false;
        
        for(int i = 0; i < room.get(value).size(); i ++){
            if (escapable(room, room.get(value).get(i), hasChecked)) return true;
        }
        return false;
    }

    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        HashMap<Integer, ArrayList<Point>> room = new HashMap<Integer, ArrayList<Point>>();
        boolean[][] hasChecked = new boolean[row][col];
        
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                int value = input.nextInt();
                if(!room.containsKey(value)) room.put(value, new ArrayList<Point>());
                room.get(value).add(new Point(i+1,j+1));
                hasChecked[i][j] = false;
            }
        }
        
        if(escapable(room, new Point(row, col), hasChecked)){
            System.out.println("Escapable");
        }
        else{
            System.out.println("Not escapable");
        }
        
    }
}