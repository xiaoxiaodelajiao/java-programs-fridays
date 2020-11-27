package snakegame;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Random;

class Input extends Frame implements KeyListener {
    private TextArea area;
    Point direction;

    Input() {
        area = new TextArea();
        area.addKeyListener(this);
        setSize(100,100);
        add(area);
        setLayout(null);
        setVisible(true);
        direction = new Point(0, -1);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && direction.y != 1) {
            direction.x = 0;
            direction.y = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction.y != -1) {
            direction.x = 0;
            direction.y = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction.x != 1) {
            direction.x = -1;
            direction.y = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction.x != -1) {
            direction.x = 1;
            direction.y = 0;
        }
    }


    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

class Snake{
    Node head;
    Node tail;
    int growAmount = 2;
    
    Snake(){
        head = new Node(new Point(10, 19), null);
        tail = head;
    }
    
    int move(Point direction){
        head.next = new Node(new Point(head.coords.x + direction.x, head.coords.y + direction.y), null);
        head = head.next;
        if(growAmount>0){
            growAmount--;
        }
        else {
            tail = tail.next;
        }
        if(head.coords.x < 0 || head.coords.x > 19 || head.coords.y < 0 || head.coords.y > 19){
            System.out.println("Game over loser");
            return -1;
        }
        
        return 1;
    }
    
    void grow(){
        growAmount++;
    }
}

class Game {
    char[][] grid = new char[20][20];
    Snake snake = new Snake();
    Point food = new Point(0, 0);
    BufferedOutputStream buffer = new BufferedOutputStream(System.out);
    Input input = new Input();
    int status = 1;
    Random random = new Random();
    int score = 3;
    
    Game(){
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 20; j ++){
                grid[i][j] = ' ';
            }
        }
        grid[19][10] = 'O';
        createFood();
    }
    
    int updateState(){
        grid[snake.tail.coords.y][snake.tail.coords.x] = ' ';
        status = snake.move(input.direction);
        if(status == -1)return -1;
        if(grid[snake.head.coords.y][snake.head.coords.x] == 'O'){
            System.out.println("Game over");
            return -1;
        }
        grid[snake.head.coords.y][snake.head.coords.x] = 'O';
        grid[snake.tail.coords.y][snake.tail.coords.x] = 'O';
        if(snake.head.coords.x == food.x && snake.head.coords.y == food.y){
            snake.grow();
            score++;
            createFood();
        }
        
        return 1;
    }
    
    void printState() throws IOException{
        buffer.write(("Score: "+score+'\n').getBytes());
        for(int i = 0; i < 22; i ++){
            buffer.write('_');
        }
        buffer.write('\n');
        for(int i = 0; i < 20; i ++){
            buffer.write('|');
            for(int j = 0; j < 20; j ++){
                buffer.write(grid[i][j]);
            }
            buffer.write('|');
            buffer.write('\n');
        }
        for(int i = 0; i < 22; i ++){
            buffer.write('_');
        }
        buffer.write('\n');
        buffer.flush();
    }
    
    void startGame() throws IOException, InterruptedException{
        while(true){
            if(this.updateState() == -1) return;
            printState();
            Thread.sleep(200);
        }
    }
    
    void createFood(){
        int x = random.nextInt(20);
        int y = random.nextInt(20);
        while(grid[y][x] == 'O'){
            x ++;
            if(x == 20){
                x = 0;
                y ++;
                if(y == 20){
                    y = 0;
                }
            }
        }
        food.x = x;
        food.y = y;
        grid[y][x] = 'F';
    }
}

class Node{
    Point coords;
    Node next;
    
    Node(Point coords, Node next){
        this.coords = coords;
        this.next = next;
    }
}

class Point{
    int x;
    int y;
    
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class SnakeGame{
    public static void main(String[] args) throws IOException, InterruptedException{
        Game snakeGame = new Game();
        snakeGame.startGame();
    }
}