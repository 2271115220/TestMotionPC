package cn.jianke.socket.tcp;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Test extends JComponent {
    public void a() {
        addMouseListener(new mouseOperate());
        addMouseMotionListener(new mouseOperate());
    }
    class mouseOperate extends MouseAdapter implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
//	setVisible(true);
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            int sx = e.getX();
            int sy = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
            int fx = e.getX();
            int fy = e.getY();
            repaint();
        }
    }

    public static void main(String[] args) {
        Test test=new Test();
        test.a();
    }
}

