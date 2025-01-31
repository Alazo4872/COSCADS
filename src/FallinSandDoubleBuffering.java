/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.swing.JPanel;

/**
    * A solution to the flickering problem
    * is a standard technique from graphics programming called double buffering:
    * Create an “offscreen” image that has the same size as the visible window and obtain
    * a Graphics object to draw onto it. Paint the entire frame onto the offscreen image rather than onto the visible window.
    * At the end of p
    * Change Canvas to JPanel (improves flickering a lot)
    * Extensions: Add wind to the model: if a particle has nothing to its right, move it that direction,
stopping at the edge. Play around with making drifts and dunes.
• Once you have wind, try applying to only pixels that have nothing above them, so
the top layer of a structure can continuously erode away.
 **/
public class FallinSandDoubleBuffering extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private final int WIDTH = 640;
    private final int HEIGHT = 240;
    private final int SIZE = 2;

    private final boolean[][] sand = new boolean[HEIGHT][WIDTH];
    private boolean active = false;

    private int x;
    private int y;

    //Create an offscreen image
    public Image offscreen; 
        
    //Get a graphics to draw onto the image
    public Graphics og;
        
    //Constructor
    public FallinSandDoubleBuffering() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
       // setSize(WIDTH, HEIGHT);
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
        g.drawImage(offscreen, 0, 0, this);
    }

    public void draw(){
        offscreen = createImage(this.WIDTH, this.HEIGHT);
        og = offscreen.getGraphics();
         //Fill the background
        og.setColor(Color.WHITE);
        og.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        //og.setColor(Color.BLACK);
        
        //Draw each sand particle - we'll use different colors here - let the students experiement with random color generating functions
        for (int r = 0; r < this.HEIGHT; r++) {
             for (int c = 0; c < this.WIDTH; c++) {
                if (this.sand[r][c]) {
                    float red = c / (float) this.WIDTH;
                    float green = r / (float) this.HEIGHT;
                    float blue = .5f;
                    og.setColor(new Color(red, green, blue));
                    og.fillRect(c, r, this.SIZE, this.SIZE);
                 }
             }
        }
    }
    //The main game loop
    public void run() {
        boolean running = true;
        while (running) {
//            try {
//                Thread.sleep(1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
           
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
            draw();
            repaint();
        }
    }
}
