/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author mZaki
 */
public class FallingSand extends Canvas implements MouseListener, MouseMotionListener {

    private final int WIDTH = 640;
    private final int HEIGHT = 240;
    private final int SIZE = 2;

    private final boolean[][] sand = new boolean[HEIGHT][WIDTH];
    private boolean active = false;

    private int x;
    private int y;

    //Constructor
    public FallingSand() {
        setSize(WIDTH, HEIGHT);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    //Methods to listen to mouse events
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        this.active = true;
    }

    public void mouseDragged(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
    }
    
    public void mouseReleased(MouseEvent e) {
        this.active = false;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
   
    
    //Draw objects to the screen
    public void paint(Graphics g) {
        //Fill the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        g.setColor(Color.BLACK);
        
        //Draw each sand particle
        for (int r = 0; r < this.HEIGHT; r++) {
             for (int c = 0; c < this.WIDTH; c++) {
                if (this.sand[r][c]) {
                    g.fillRect(c, r, this.SIZE, this.SIZE);
                 }
             }
        }
    }

    //The main game loop
    /*There are a couple subtleties in this implementation. First, observe that the sand matrix
    is indexed using y for the row dimension and x for the column dimension. This makes
    sense—row 0 of the matrix corresponds to a y value of 0—but it’s easy to overlook.
    Second, the sand-dropping loop starts from the bottom and works its way up through
    the matrix; if you have multiple grains of sand in the same column, the lower ones will
    fall first and make room for the upper ones to move down. To finish the program, add
    a paint method that draws each sand particle as a pixel. */

    public void run() {
        boolean running = true;
        while (running) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Drop sand at the current mouse position
            //The y position is used as the row of the sand
            //The x is the column!
            if (this.active) {
                this.sand[this.y][this.x] = true;
            }

            //Falling Physics
            for (int r = this.HEIGHT - 2; r >= 0; r--) {
                for (int c = 1; c < this.WIDTH - 1; c++) {

                    if (!this.sand[r][c]) {
                        continue;
                    }

                    //try to move down
                    if (!this.sand[r + 1][c]) {
                        this.sand[r][c] = false;
                        this.sand[r + 1][c] = true;
                    } 
                    
                    // Try to move down-left
                    else if (!this.sand[r + 1][c - 1]) {
                        this.sand[r][c] = false;
                        this.sand[r + 1][c - 1] = true;
                    }
                    
                    // Try to move down-right
                    else if (!this.sand[r + 1][c + 1]) {
                        this.sand[r][c] = false;
                        this.sand[r + 1][c + 1] = true;
                    }
                }
            }
            //Draw
            repaint();
        }
    }
}
