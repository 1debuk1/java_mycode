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
        setTitle("Sticky Note");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true); 
        setLayout(new BorderLayout());

        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titleBar.setBackground(Color.YELLOW);

        
        JButton closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> dispose());

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

       
        add(titleBar, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
