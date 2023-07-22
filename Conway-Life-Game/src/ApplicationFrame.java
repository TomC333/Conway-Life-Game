import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JFrame {

    private static final int HGAP_VGAP = 4;

    private CellPanel view;

    private void createBottomPannel(){

        JPanel bottom = new JPanel();
        JButton start = new JButton("Start");
        JButton end = new JButton("End");

        bottom.add(start);
        bottom.add(end);

        // When start button is pressed editing is enabled
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                end.setEnabled(true);
                start.setEnabled(false);

                view.enableGrid();
            }
        });

        // When end button is pressed editing is disabled
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                end.setEnabled(false);
                start.setEnabled(true);

                view.disableGrid();

            }
        });

        add(bottom, BorderLayout.SOUTH);
    }

    public ApplicationFrame(boolean[][] grid, int row, int col, int size){

        // Trying to change look of GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        setLayout(new BorderLayout(HGAP_VGAP, HGAP_VGAP));

        // Adding few panels on screen
        view = new CellPanel(grid, row, col, size);
        add(view, BorderLayout.NORTH);
        createBottomPannel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
