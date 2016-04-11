import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by Boran1 on 27.3.2016.
 * This class will simply display the SOS game as a grid.
 */
public class SOSCanvas extends JPanel {

    //properties
    protected SOS board;
    protected Font font;
    protected double ratio1 = 0.58;
    protected double ratio2 = 0.70;
    protected double ratio3 = 0.38;
    protected int side = 400;
    protected int x = 50;
    protected int y = 50;

    //constructor
    public SOSCanvas(SOS board) {
        this.board = board;
        setSize(600, 600);
        setPreferredSize(new Dimension(420, 420));
        font = new Font("default", Font.ITALIC, 20);
    }


    public SOS getBoard(){
        return board;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 10 ; i <= side + 10  ; i = i + side/board.getDimension() ){
            g.drawLine(10, i, side + 10 , i);
            g.drawLine(i, 10, i, side + 10);
        }

        //cell contents
        y = (int) (ratio2 * side / board.getDimension());
        for(int i = 0 ; i < board.getDimension() ; i++ ){
            x = (int) (ratio1 * side / board.getDimension());
            for(int j = 0 ; j < board.getDimension() ; j++ ){
                g.setFont(font);
                System.out.println(x + " " + y);
                g.drawString("" + board.getCellContents(i, j), x , y);
                x = x + side/board.getDimension();
            }
            y = y + side/board.getDimension();
        }
    }


}

