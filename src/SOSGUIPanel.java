import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boran1 on 28.3.2016.
 * This class will include an instance of the SOSCanvas class,
 * as well as the other UI components necessary to show the player names and scores,
 * and the radio buttons for selecting the letter to play.
 */
public class SOSGUIPanel extends JPanel implements ActionListener{

    //properties
    SOSCanvas canvas;
    SOS sos;
    String player1 = "";
    String player2 = "";
    JRadioButton S, O;
    JLabel first, second, firstInfo;
    int keepRow = 1;
    int keepColumn = 1;
    int counter1;
    int counter2;
    char letter ;
    String winner;
    JTextField firstPlayer = new JTextField(4);
    JTextField secondPlayer = new JTextField(4);
    JTextField thirdPlayer = new JTextField(2);
    JButton firstOk = new JButton("ok");
    JButton secondOk = new JButton("ok");
    MouseListener mouseListener = new MouseSOS();

    //constructor
    public SOSGUIPanel(SOS sos) {
        this.sos = sos;

        //setting info labels
        firstInfo = new JLabel("Enter the names: ");
        add(firstInfo);
        firstInfo = new JLabel("GridSize");

        //adding getting names texFields
        add(firstPlayer);
        add(secondPlayer);

        //adding button
        add(firstOk);
        firstOk.addActionListener(this);
        secondOk.addActionListener(this);

        add(firstInfo);
        add(thirdPlayer);
        add(secondOk);

        //adding canvas to GUIPanel
        canvas = new SOSCanvas(sos);
        canvas.addMouseListener(mouseListener);
        add(canvas);

        //sos = canvas.sosTry;

        //radioButtons
        S = new JRadioButton("S");
        O = new JRadioButton("O");
        S.addActionListener(this);
        O.addActionListener(this);

        //JLabels to show score for each person
        first = new JLabel(player1 + ": " + sos.getPlayerScore1() +  "                 " );
        second = new JLabel("                 " + player2 + ": " + sos.getPlayerScore2() );
        first.setOpaque(true);
        second.setOpaque(true);

        //setting background for first player
        first.setBackground(Color.RED);

        //adding them
        add(first);
        add(S);
        add(O);
        add(second);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //writing players names
        if(e.getSource() == firstOk){
            player1 = firstPlayer.getText();
            player2 = secondPlayer.getText();
            first.setText(player1 + ": " + sos.getPlayerScore1() +  "                 " );
            second.setText("                 " + player2 + ": " + sos.getPlayerScore2() );
        }
        //changing grid size by user want
        if(e.getSource() == secondOk){
            canvas.board = new SOS(Integer.parseInt(thirdPlayer.getText()));
            sos = canvas.board;
            canvas.repaint();
        }

        //checking radio buttons for S or O
        if (e.getSource() == S){
            letter = 's';
            O.setSelected(false);
        }

        else if (e.getSource() == O){
            letter = 'o';
            S.setSelected(false);
        }

        repaint();
    }

    private class MouseSOS extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e){

            //checking ROW
            counter1 = 1;
            for(int i = 10 ; i <= 410 ; i = i + 400 / sos.getDimension() ){
                if(e.getY()> i && e.getY() < i+400 / sos.getDimension()){
                    keepRow = counter1;
                }
                counter1 += 1;
            }

            //checking COLUMN
            counter2 = 1;
            for(int i = 10 ; i <= 410 ; i = i + 400 / sos.getDimension() ){
                if(e.getX() > i && e.getX() < i + 400 / sos.getDimension()){
                    keepColumn = counter2;
                }
                counter2 += 1;
            }

            //Playing
            sos.play(letter,  keepRow ,keepColumn);
            repaint();

            //setting points again
            first.setText(player1 + ": " + sos.getPlayerScore1() +  "                 " );
            second.setText("                 " + player2 + ": " + sos.getPlayerScore2() );

            //changing background color according to player
            if(sos.getTurn() == 1){
                first.setBackground(Color.RED);
                second.setBackground(Color.WHITE);
            }
            else{
                first.setBackground(Color.WHITE);
                second.setBackground(Color.RED);
            }

            //checking winner
            if(sos.getPlayerScore1() > sos.getPlayerScore2())
                winner = player1 + " is winner";
            else if(sos.getPlayerScore1() < sos.getPlayerScore2())
                winner = player2 + " is winner";
            else if(sos.getPlayerScore1() == sos.getPlayerScore2())
                winner = "It is a draw!";

            //checking GameOver or not
            if(sos.isGameOver()){
                JOptionPane.showMessageDialog (null, winner,"GAME OVER!", JOptionPane.YES_NO_OPTION);
                //RESTART
                int reply = JOptionPane.showConfirmDialog(null,"Do you want to play again?", "GAME OVER!", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    canvas.board = new SOS(sos.getDimension());
                    sos = canvas.board;
                    //initializing values again
                    player1 = "";
                    player2 = "";
                    firstPlayer.setText("");
                    secondPlayer.setText("");
                    first.setText(player1 + ": " + sos.getPlayerScore1() +  "                 " );
                    second.setText("                 " + player2 + ": " + sos.getPlayerScore2() );
                    repaint();

                }
                else
                    System.exit(0);
            }
            repaint();
        }
    }
}