import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JLabel {
    private int player;
    private int computer;  
    
    
    public ScoreBoard() 
    {
        player = 0;
        computer = 0;
        
        setHorizontalAlignment(JLabel.CENTER);
        update();
    }
    
    public void update() 
    {
        setText("Player: " + player + " Computer: " + computer);
    }
    
    public void gameOver(int winner)
    {
        if (winner == 2) {
            player++;
        } 
        if(winner==3){
            computer++;
        }
        update();
    }
    
    public void increasePlayer()
    {
        player++; 
        
    }
    
    public void increaseComputer()
    {
        computer++; 
    }
    
    public void reset() 
    {
        player = 0;
        computer = 0;
        update();
    }
}