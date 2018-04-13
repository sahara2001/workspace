import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Test class for NaturalNumber calculator GUI (view in MVC).
 *
 * @author Heming Sun
 */
public final class NNCalcViewLab extends JFrame implements ActionListener {

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower;

    private JButton bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No-argument constructor.
     */
    public NNCalcViewLab() {

        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        // TODO: fill in rest of body, following outline in comments

        /*
         * Create widgets
         */

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */
        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        JScrollPane topPanel = new JScrollPane(this.tTop);

        JScrollPane botPanel = new JScrollPane(this.tBottom);

        /*
         * Create main button panel
         */
        this.bDigits = new JButton[10];
        for (int i = 0; i < 10; i++) {
            this.bDigits[i] = new JButton(Integer.toString(i));
        }

        JPanel buttonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));

        for (JButton i : this.bDigits) {
            buttonPanel.add(i);
        }
        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */

        this.bPower = new JButton("Power");
        this.bRoot = new JButton("Root");
        this.bMultiply = new JButton("*");
        this.bDivide = new JButton("/");

        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        buttonPanel.add(this.bPower);
        buttonPanel.add(this.bRoot);
        buttonPanel.add(this.bAdd);
        buttonPanel.add(this.bSubtract);
        buttonPanel.add(this.bMultiply);
        buttonPanel.add(this.bDivide);
        /*
         * Create side button panel
         */
        JPanel sidePanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));
        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */
        this.bClear = new JButton("Clear");
        this.bSwap = new JButton("Swap");
        this.bEnter = new JButton("Enter");

        sidePanel.add(this.bClear);
        sidePanel.add(this.bSwap);
        sidePanel.add(this.bEnter);
        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */

        /*
         * Add the other two button panels to the combined button panel
         */

        /*
         * Organize main window
         */
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(buttonPanel);
        panel.add(sidePanel);
        this.add(topPanel);
        this.add(botPanel);
        this.add(panel);

        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */

        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */

        this.bDivide.setEnabled(false);
        this.bPower.setEnabled(false);
        this.bAdd.setEnabled(true);
        this.bClear.setEnabled(true);
        this.bEnter.setEnabled(true);
        this.bMultiply.setEnabled(true);
        this.bRoot.setEnabled(false);
        this.bSubtract.setEnabled(true);
        this.bSwap.setEnabled(true);

        this.bClear.addActionListener(this);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(this,
                "You pressed: " + event.getActionCommand());
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        NNCalcViewLab view = new NNCalcViewLab();
    }

}
