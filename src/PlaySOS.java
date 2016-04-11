import javax.swing.*;
/**
 * Created by Boran1 on 4.4.2016.
 */
public class PlaySOS {
    //properties
    static SOSGUIPanel panelSOS ;
    static JFrame frame;

    public static void main(String[] args) {
        //creating frame
        frame = new JFrame("SOS Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 600);

        //creating and adding SOSGUIPanel
        panelSOS = new SOSGUIPanel(new SOS(5));
        frame.add(panelSOS);
        frame.setVisible(true);
    }
}
