import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private TTTBoard board; 
    private ScoreBoard score;
    private JButton btnGame;
    private JButton btnReset;
    
    public GUI() 
    {
        
        
        super(); 
        setLayout(new BorderLayout());

        
        JPanel pnlSouth = new JPanel();
        score = new ScoreBoard();
        board = new TTTBoard(score);
        add(board, BorderLayout.CENTER);
        pnlSouth.add(score, BorderLayout.SOUTH);
        btnGame = new JButton("New Game");
        pnlSouth.add(btnGame);
        btnGame.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                          board.reset();
                }
            }
        );

        btnReset = new JButton("Reset");
        pnlSouth.add(btnReset);
        btnReset.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                  
                    board.reset();
                    score.reset();
                }
            }
        );
        add(pnlSouth, BorderLayout.SOUTH);

        setTitle("Tic-Tac-Toe");
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}