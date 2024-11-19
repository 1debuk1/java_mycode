import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StickyNoteApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Sticky Note window
            StickyNote stickyNote = new StickyNote();
            stickyNote.setVisible(true);
        });
    }
}

class StickyNote extends JFrame {
    private JTextArea textArea;

    public StickyNote() {
        // Set up the frame
        setTitle("Sticky Note");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true); // Keep the window always on top
        setUndecorated(true); // Remove title bar for cleaner look
        setLayout(new BorderLayout());

        // Create a movable title bar
        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titleBar.setBackground(Color.YELLOW);

        // Close button
        JButton closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> dispose());

        // Add drag functionality to the title bar
        titleBar.addMouseMotionListener(new MouseAdapter() {
            Point dragStart = null;

            @Override
            public void mouseDragged(MouseEvent e) {
                Point dragEnd = e.getLocationOnScreen();
                if (dragStart != null) {
                    int x = dragEnd.x - dragStart.x;
                    int y = dragEnd.y - dragStart.y;
                    setLocation(getX() + x, getY() + y);
                    dragStart = dragEnd;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getLocationOnScreen();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragStart = null;
            }
        });

        titleBar.add(closeButton);

        // Create the text area
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(new Color(255, 255, 200));

        // Add components to the frame
        add(titleBar, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
