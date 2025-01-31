package FallingSand;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Image;
import javax.swing.JFrame;

public class FallingSandDoubleBuffering extends Canvas implements MouseListener, MouseMotionListener {
    //sand and dimensions
    private final int WIDTH = 640;
    private final int HEIGHT = 240;
    private final boolean[][] SAND = new boolean[HEIGHT][WIDTH];

    //mouse info
    private boolean active = false;
    private int x, y;

    //variables for double buffering
    public Image offscreen;
    public Graphics og;

    //constructor
    public FallingSandDoubleBuffering() {
        this.setSize(WIDTH, HEIGHT);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    //called whenever mouse is pressed down
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        this.active = true;
    }

    //called when the mouse is moved while the button is pressed down
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    //called when the mouse button is released
    public void mouseReleased(MouseEvent e) {
        this.active = false;
    }

    //unused interface methods
    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseMoved (MouseEvent e) {}

    public void paint(Graphics g) {
        g.drawImage(offscreen, 0, 0, this);
    }

    //graphics implementation
    public void draw() {
        //filling the background
        offscreen = createImage(this.WIDTH, this.HEIGHT);
        og = offscreen.getGraphics();

        og.setColor(Color.DARK_GRAY);
        og.fillRect(0, 0, this.WIDTH, this.HEIGHT);

        //drawing sand
        og.setColor(Color.BLUE);
        for(int r = 0; r < this.HEIGHT; r++) {
            for(int c = 0; c < this.WIDTH; c++) {
                if(this.SAND[r][c]) {

                    float red = 0.5f;
                    float green = c/ (float)this.WIDTH;
                    float blue = r/ (float)this.HEIGHT;

                    og.setColor(new Color(red, green, blue));
                    int SIZE = 2;
                    og.fillRect(c, r, SIZE, SIZE);
                }
            }
        }
    }

    public void run() {
        while(true) {
            try{
                Thread.sleep(1);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            if(this.active) {
                this.SAND[this.y][this.x] = true;
            }

            for(int r = this.HEIGHT - 2; r >= 0; r--){
                for(int c = 1; c < this.WIDTH - 1; c++) {
                    //if the point should not have sand there
                    if(!this.SAND[r][c]) {
                        continue;
                    }
                    //if there is sand there and an empty space below it
                    if(!this.SAND[r + 1][c]) {
                        this.SAND[r][c] = false;
                        this.SAND[r + 1][c] = true;
                    }
                    //if there is sand there and below it, but an empty space to the bottom left
                    else if(!this.SAND[r + 1][c - 1]) {
                        this.SAND[r][c] = false;
                        this.SAND[r + 1][c - 1] = true;
                    }
                    //if there is sand there, below it, and to the left, but an empty space to the bottom right
                    else if(!this.SAND[r + 1][c + 1]) {
                        this.SAND[r][c] = false;
                        this.SAND[r + 1][c + 1] = true;
                    }
                }
            }
            draw();
            repaint();
        }
    }
}
