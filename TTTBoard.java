import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TTTBoard extends JPanel implements ActionListener {
    private TTTSquare[][] squares;
    private ScoreBoard gameScore;
    private Random random = new Random();
    private int turn = 0;

    public TTTBoard(ScoreBoard sb) 
    {
        super(new GridLayout(3, 3));
        squares = new TTTSquare[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                squares[r][c] = new TTTSquare();
                add(squares[r][c]);
                squares[r][c].addActionListener(this); 
            }
        }
        gameScore = sb; 
    }

    public void actionPerformed(ActionEvent e)
    {
        TTTSquare btn = (TTTSquare) e.getSource(); 
        
        if(btn.isAvailable()){
            btn.clicked();
            turn++;
            int block = checkForTwo(2); 
            int win = checkForTwo(3); 

            if (winningMove(2)) {
                gameScore.gameOver(2);
                
                reset(); 
            } else {
                if (turn < 5) {
                    if (win == -1) {
                        if (block == -1) {
                            int r;
                            int c;
                            do {
                                r = random.nextInt(3);
                                c = random.nextInt(3);
                            } while (!squares[r][c].isAvailable());
                            
                            squares[r][c].computer(); 
                            
                        } else {
                            goodPlay(block);
                        }
                    } else {
                        goodPlay(win); 
                    }
                    if (winningMove(3)) {
                        gameScore.gameOver(3);
                        
                        reset(); 
                    }
                } else {
                   
                    reset();
                }
            }
        }

    }

    

    private void goodPlay(int greatSquare)
    {
        int pos = greatSquare;
        int r = pos / 3;
        int c = pos % 3; 
        squares[r][c].computer();
    }

    private boolean winningMove(int player)
    {

        for(int r = 0; r < 3; r++) {
            if(squares[r][1].getPlayer() == player) {
                if(squares[r][0].getPlayer() == player && squares[r][2].getPlayer() == player) {
                    return true;
                }
            }
        }
        for(int c = 0; c < 3; c++) {
            if(squares[1][c].getPlayer() == player) {
                if(squares[0][c].getPlayer() == player && squares[2][c].getPlayer() == player) {
                    return true;
                }
            }
        }
        if(squares[1][1].getPlayer() == player) {
            if(squares[0][0].getPlayer() == player && squares[2][2].getPlayer() == player) {
                return true;
            }
            if(squares[0][2].getPlayer() == player && squares[2][0].getPlayer() == player) {
                return true;
            }
        }
        return false;
    }

    private int checkForTwo(int player)
    {
        int productt = player * player;

        for (int r = 0; r < 3; r++) { 
            int product = 1; 
            for (int c = 0; c < 3; c++) {
                product *= squares[r][c].getPlayer();
            }
            if (product == productt) { 
                for (int c2 = 0; c2 < 3; c2++) {
                    if (squares[r][c2].isAvailable()) {
                        return r * 3 + c2; 
                    }
                }
            }
        }

        for (int c = 0; c < 3; c++) { 
            int product = 1;
            for (int r = 0; r < 3; r++) {
                product *= squares[r][c].getPlayer();
                if (product == productt) {
                    for (int r2 = 0; r2 < 3; r2++) {
                        if (squares[r2][c].isAvailable()) {
                            return r2 * 3 + c;
                        }
                    }
                }
            }
        }


        int diagonal = 1;
        for (int d = 0; d < 3; d++) {
            diagonal *= squares[d][d].getPlayer();
        }
        if (diagonal == productt) { 
            for (int diag = 0; diag < 3; diag++) {
                if (squares[diag][diag].isAvailable()) {
                    return diag * 3 + diag;
                }
            }
        }


        int diagonal2 = 1;
        for (int r= 0; r< 3; r++) {
            int c = 2-r; 
            diagonal2 *= squares[r][c].getPlayer();
        }
        if (diagonal2 == productt) {
            for(int r2 = 0;r2<3;r2++){
                int c2 = 2-r2; 
                if(squares[r2][c2].isAvailable()){ 
                    return r2*3+c2;
                }
            }
        }

        return -1;
    }

    public void reset()
    {

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                squares[r][c].reset();
            }
        }
        turn = 0;
    }
}