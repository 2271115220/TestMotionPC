package cn.jianke.socket.tcp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class BezierFrame extends JFrame
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new JFrame();
                frame.setTitle("BezierTest");
                frame.setSize(264,360);
                BezierPanel bezier = new BezierPanel();
                bezier.setBorder(new LineBorder(Color.black));
                bezier.setPreferredSize(new Dimension(254, 254));
                frame.add(bezier);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    
}