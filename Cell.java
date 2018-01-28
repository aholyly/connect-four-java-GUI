package hw8;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Helper class for Connect Four
 * @author mert
 */
public class Cell extends JPanel{
  
    public JButton[][] squares;
    private int size;
    
    /**
     * Constructor for cells
     * @param boardSize boardsize
     */
    public Cell(int boardSize){
        super(new GridLayout(boardSize+1,boardSize));
        size = boardSize;
        
        squares = new JButton[boardSize+1][boardSize];
        
        setSize(600,600);
        
        for (int i = 0; i < boardSize+1; i++) {
            for (int j = 0; j < boardSize; j++) {
                squares[i][j] = new JButton();
                add(squares[i][j]);
                if (i == 0){
                    char letter = (char)('A'+j);
                    squares[i][j].setBackground(Color.lightGray);
                    squares[i][j].setText(String.valueOf(letter));
                }
                else
                     squares[i][j].setBackground(Color.white);
            }
        }
    }
    
    /**
     * Creates a new frame and fill with boards
     */
    public void initBoard(){ 
        String gameStyle = "One Player";
        String headerName = "Connect Four-"+gameStyle+"-"+size+"x"+size;
        JFrame frame = new JFrame(headerName);
        frame.setLayout(null);
        
        JLabel label1 = new JLabel("Welcome to Connect Four Game!");
        label1.setHorizontalAlignment(0);
        label1.setSize(800, 10);
        label1.setLocation(10, 10);
        
        Cell board = new Cell(10);
        board.setLocation(0, 100);
        
        
        frame.getContentPane().add(label1);
        frame.getContentPane().add(board);
        frame.setSize(800,800);
        frame.setVisible(true);
    }
    
    
}
