/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;
import javafx.application.Platform;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Connect Four Game implemented in Java
 * @author mert
 */
public class ConnectFour extends JPanel{
    private int boardSize;
    private String gameStyle;
    private char gameStyleCh;
    private String moveFromBox;
    private char[][] board;
    private JButton playButton;
    Cell GUIBoard;
    private JLabel label1;
    private JTextField moveGetter;
    private char move;
    private int turn = 1;
    
    /**
     * Contructor of Connect Four
     */
    public ConnectFour(){
        super(new GridLayout(3,1));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel label1, label2;
        
        label1 = new JLabel("Welcome to Connect Four Game!");
        label1.setHorizontalAlignment(0);
        label2 = new JLabel("Please enter a board size and select game style:");
        label2.setHorizontalAlignment(0);
        
        JButton onePlayerButton = new JButton("One Player");
        JButton twoPlayerButton = new JButton("Two Player");
        
        JTextField sizeGetterBox = new JTextField(20);
           
        buttonPanel.add(sizeGetterBox);
        buttonPanel.add(onePlayerButton);
        buttonPanel.add(twoPlayerButton);
        
        add(label1);
        add(label2);
        add(buttonPanel);
        
        sizeGetterBox.setVisible(true);
        
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = Integer.parseInt(sizeGetterBox.getText());
                gameStyle = "One Player";
                gameStyleCh = gameStyle.charAt(0);
                initBoard();
            }
        });
        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSize = Integer.parseInt(sizeGetterBox.getText());
                gameStyle = "Two Player";
                gameStyleCh = gameStyle.charAt(0);
                initBoard();
            }
        });
       
    }
   
    /**
     * // set all cells with '.'
     */
    public void initBoard(){
        turn = 1;
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i)
	{
            for (int j = 0; j < boardSize; ++j)
            {
                board[i][j] = '.';
            }
	}
        
        String headerName = "Connect Four-"+gameStyle+"-"+boardSize+"x"+boardSize;
       
        JFrame frame = new JFrame(headerName);
        frame.setLayout(null);

        label1 = new JLabel("Enter a move:");
        label1.setHorizontalAlignment(2);
        label1.setSize(300, 10);
        label1.setLocation(10, 10);
        
       
        
        moveGetter = new JTextField(1);
       
        moveGetter.setHorizontalAlignment(0);
        moveGetter.setSize(50, 50);
        moveGetter.setLocation(10, 40);
        
        playButton = new JButton("Play");
        playButton.setHorizontalAlignment(0);
        playButton.setSize(100, 50);
        playButton.setLocation(75, 40);
        

        GUIBoard = new Cell(boardSize);
        GUIBoard.setLocation(0, 100);

        frame.getContentPane().add(label1);
        frame.getContentPane().add(moveGetter);
        frame.getContentPane().add(playButton);
        frame.getContentPane().add(GUIBoard);
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if ( isFinished(0) == 0 ) {
                    if (gameStyleCh == 'O') {
                        playOnePlayer();
                        if ( isFinished(0) == 0 ){
                            playOnePlayer();
                        }
                    }
                    if (gameStyleCh == 'T') {
                        playTwoPlayer();
                    }
                    
                    System.out.println("-"+isFinished(0));
                     
                    if ( isFinished(0) != 0 ){
                        System.out.println("--"+isFinished(0));
                        JOptionPane finish = new JOptionPane();
                        //stores winner
                        
                        int finishSituation = isFinished(0);
                         isFinished(1);
                        System.out.println("---"+finishSituation);

                        if(finishSituation == 1)
                            finish.showMessageDialog(null, "GAME OVER! Winner: Player1");
                        if(finishSituation == 2)
                            finish.showMessageDialog(null, "GAME OVER! Winner: Player2");
                        if(finishSituation == 3)
                            finish.showMessageDialog(null, "Board is full! No winner (:");

                       
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                     }
                }
               
               
            }
        });
      
        
        frame.setSize(800,800);
        moveGetter.setVisible(true);
        frame.setVisible(true);
    }
    
    /**
     * 
     * @param changeCondition change board color
     * @return who wins
     */
    public int isFinished(int changeCondition){
        if( isFour(changeCondition) == 1)
            return 1;
	if( isFour(changeCondition) == 2)
            return 2;
	if( isFull() )
            return 3;
	else 
            return 0;
    }
    
    /**
     * 
     * @return true if board full
     */
    Boolean isFull(){
        int total = 0;

	for (int i = 0; i < boardSize; ++i)
	{
            for (int j = 0; j < boardSize; ++j)
            {
                if(board[i][j] != '.')
                    total++;
            }
	}

	if( total == (boardSize*boardSize) )
            return true;
	else
            return false;
    }
    
    /**
     * 
     * @param changeCondition change board color
     * @return true if four cells connected
     */
    int isFour(int changeCondition){
	//HORIZONTAL CHECK
	for (int i = 0; i < boardSize; ++i)
	{
            for (int j = 0; j < boardSize-3; ++j)
            {
                //check
                if(board[i][j+0] == 'X' && board[i][j+1] == 'X' 
                    && board[i][j+2] == 'X' && board[i][j+3] == 'X')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i][j+0] = 'x';
                        board[i][j+1] = 'x';
                        board[i][j+2] = 'x';
                        board[i][j+3] = 'x';
                        GUIBoard.squares[i+1][j+0].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+1].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+2].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+3].setBackground(Color.yellow);
                    }
                    return 1;
                }
                //check
                if(board[i][j+0] == 'O' && board[i][j+1] == 'O' 
                    && board[i][j+2] == 'O' && board[i][j+3] == 'O')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i][j+0] = 'o';
                        board[i][j+1] = 'o';
                        board[i][j+2] = 'o';
                        board[i][j+3] = 'o';
                        GUIBoard.squares[i+1][j+0].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+1].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+2].setBackground(Color.yellow);
                        GUIBoard.squares[i+1][j+3].setBackground(Color.yellow);
                        
                    }
                    return 2;
                }
            }
	}


	//VERTICAL CHECK
	for (int j = 0; j < boardSize; ++j)
	{
            for (int i = 0; i < boardSize-3; ++i)
            {
                //check
                if(board[i+0][j] == 'X' && board[i+1][j] == 'X' 
                    && board[i+2][j] == 'X' && board[i+3][j] == 'X')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j] = 'x';
                        board[i+1][j] = 'x';
                        board[i+2][j] = 'x';
                        board[i+3][j] = 'x';
                        GUIBoard.squares[i+1][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j].setBackground(Color.yellow);
                    }
                    return 1;
                }
                //check
                if(board[i+0][j] == 'O' && board[i+1][j] == 'O' 
                    && board[i+2][j] == 'O' && board[i+3][j] == 'O')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j] = 'o';
                        board[i+1][j] = 'o';
                        board[i+2][j] = 'o';
                        board[i+3][j] = 'o';
                        GUIBoard.squares[i+1][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j].setBackground(Color.yellow);
                    }
                    return 2;
                }
            }
	}


	//DIAGONAL-LEFTtoRIGHT CHECK
	for (int i = 0; i < boardSize-3; ++i)
	{
            for (int j = 0; j < boardSize-3; ++j)
            {
                //check
                if(board[i+0][j+0] == 'X' && board[i+1][j+1] == 'X' 
                    && board[i+2][j+2] == 'X' && board[i+3][j+3] == 'X')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j+0] = 'x';
                        board[i+1][j+1] = 'x';
                        board[i+2][j+2] = 'x';
                        board[i+3][j+3] = 'x';
                        GUIBoard.squares[i+1][j+0].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j+1].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j+2].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j+3].setBackground(Color.yellow);
                    }
                    return 1;
                }
                //check
                if(board[i+0][j+0] == 'O' && board[i+1][j+1] == 'O' 
                    && board[i+2][j+2] == 'O' && board[i+3][j+3] == 'O')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j+0] = 'o';
                        board[i+1][j+1] = 'o';
                        board[i+2][j+2] = 'o';
                        board[i+3][j+3] = 'o';
                        GUIBoard.squares[i+1][j+0].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j+1].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j+2].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j+3].setBackground(Color.yellow);
                    }
                    return 2;
                }
            }
	}


	//DIAGONAL-RIGHTtoLEFT CHECK
	for (int i = 0; i < boardSize-3; ++i)
	{
            for (int j = 3; j < boardSize; ++j)
            {
                //check
                if(board[i+0][j-0] == 'X' && board[i+1][j-1] == 'X' 
                    && board[i+2][j-2] == 'X' && board[i+3][j-3] == 'X')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j-0] = 'x';
                        board[i+1][j-1] = 'x';
                        board[i+2][j-2] = 'x';
                        board[i+3][j-3] = 'x';
                        GUIBoard.squares[i+1][j-0].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j-1].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j-2].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j-3].setBackground(Color.yellow);
                    }
                    return 1;
                }
                //check
                if(board[i+0][j-0] == 'O' && board[i+1][j-1] == 'O' 
                    && board[i+2][j-2] == 'O' && board[i+3][j-3] == 'O')
                {
                    //change
                    if(changeCondition == 1)
                    {
                        board[i+0][j-0] = 'o';
                        board[i+1][j-1] = 'o';
                        board[i+2][j-2] = 'o';
                        board[i+3][j-3] = 'o';
                        GUIBoard.squares[i+1][j-0].setBackground(Color.yellow);
                        GUIBoard.squares[i+2][j-1].setBackground(Color.yellow);
                        GUIBoard.squares[i+3][j-2].setBackground(Color.yellow);
                        GUIBoard.squares[i+4][j-3].setBackground(Color.yellow);
                    }
                    return 2;
                }
            }
	}

	return 0;
}
    
    /**
     * Player plays the game with another player
     */
    public void playTwoPlayer()
    {
        label1.setText("Player"+turn+": Make move!");
        move = moveGetter.getText().toString().charAt(0);

        //makes moves
        if( makeMove() )
            changeTurn();
        else 
            label1.setText("Column is full! Try again!"); 
    }
    
    /**
     * Player plays the game with computer
     */
    public void playOnePlayer()
    {
	//takes moves from Player1 via terminal
        if(turn == 1){
            label1.setText("Player"+turn+": Make move!");
            move = moveGetter.getText().toString().charAt(0);
        }
        //gets best move for computer moves
        if(turn == 2){
            label1.setText("Player"+turn+": Make move!");
            getBestMove();
        }

        //makes moves
        if( makeMove() )
            changeTurn();
        else 
            label1.setText("Column is full! Try again!");
    }
    
    /**
     * takes board and reference move to change move with best move
     */
   public void getBestMove()
    {
        int start = 'A';
        int best = 0;

        //makes move to win game 
        for (int i = 0; i < boardSize; ++i)
        {
            move = (char)(start+i);
            if( makeMove() )
            {
                if( isFour(0) == 2 ){
                        best = 1;
                }
                undoMove();
            }
        }

        //makes move to block Player1 won
        if(best == 0)
        {
            for (int i = 0; i < boardSize; ++i)
            {
                move = (char)(start+i);
                changeTurn();
                if( makeMove() )
                {
                    if( isFour(0) == 1){
                            best = 1;	
                    }
                    undoMove();
                }
                changeTurn();
            }
        }

        //if no win or block, makes random moves
        Random rand = new Random();

        while(best == 0)
        {
            move = (char)((rand.nextInt(boardSize)) + 'A');

            if( makeMove() )
            {
                best = 1;
                //makes moves for check moves and undo that moves
                undoMove();
            }
        }
    }
   
   /**
    * clears last item in the column(letter)
    */
    public void undoMove()
    {
        int letterIndexValue = 0;

        if(move >= 'a' && move <= 'z')
            letterIndexValue = move - 'a';
        if(move >= 'A' && move <= 'Z')
            letterIndexValue = move - 'A';

        for (int i = 0; i < boardSize; ++i)
        {
            if( board[i][letterIndexValue] != '.' )
            {
                board[i][letterIndexValue] = '.';
                GUIBoard.squares[i+1][letterIndexValue].setBackground(Color.white);
                return;
            }
        }
    }
    
    /**
     * Changes turn
     */
    void changeTurn()
    {
            if(turn == 1)
            {
                    turn = 2;
                    return;
            }
            if(turn == 2)
            {
                    turn = 1;
                    return;
            }
    }
    
    /**
     * makes move and change cell color
     * @return true if move is maden
     */
    boolean makeMove(){
        int letterIndexValue = 0; 

        if(move >= 'A' && move <= 'Z')
            letterIndexValue = move - 'A';
        if(move >= 'a' && move <= 'z')
            letterIndexValue = move - 'a';

        int fullCells = 0; //count for full cells
        for (int i = boardSize; i != 0; --i)
        {
            //checks is column full or not
            if(board[i-1][letterIndexValue] == '.')
            {
                //if empty, makes move
                if(turn == 1)
                {
                    board[i-1][letterIndexValue] = 'X';
                    GUIBoard.squares[i][letterIndexValue].setBackground(Color.red);
                    return true;
                }
                if(turn == 2)
                {
                    board[i-1][letterIndexValue] = 'O';
                    GUIBoard.squares[i][letterIndexValue].setBackground(Color.blue);
                    return true;
                }
            }
            else
            {
                ++fullCells;
                if(fullCells == boardSize)
                {
                    return false;
                }
            }
        }

        return false;
    }
  
    public static void main(String[] args) {
      ConnectFour game = new ConnectFour();
      JFrame frame = new JFrame("Connect Four");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(game);
      frame.setSize(600, 600);
      frame.setVisible(true); 
    }
    
}
