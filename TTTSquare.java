import javax.swing.*;
import java.awt.*;
public class TTTSquare extends JButton {
    
    
    private int token;
    
    public TTTSquare() 
    {
        token = 1;
        update();
    }
    
    public void update() 
    {
        switch (token) {
            case 1: setText(""); break;
            case 2: setText("X"); break;
            case 3: setText("O");
        }
    }
    
    public boolean isAvailable() 
    {
        return token == 1;
    }
    
    public void clicked() 
    {
        token = 2;
        update();
    }
    
    public void computer() 
    {
        token = 3;
        update();
    }
    
    public void reset()
    {
        token = 1;
        update();
    }
    
    public int getPlayer() 
    {
        return token;
    }
    
    /*public void actionPerformed(ActionEvent e)
    {
        clicked();
    }*/
}