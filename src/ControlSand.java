import javax.swing.JFrame; 

/**
 *
 * @author mZaki
 */
public class ControlSand {
    public static void main(String[] args){
        //FallingSand game = new FallingSand();
        FallinSandDoubleBuffering game2 = new FallinSandDoubleBuffering();
        
        JFrame frame = new JFrame();
        frame.add(game2);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Run the main loop
        game2.run();
    }
    
}
