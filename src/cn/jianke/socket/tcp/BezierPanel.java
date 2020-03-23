package cn.jianke.socket.tcp;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class BezierPanel extends JComponent
{
    private static int SIZE = 8;
    private int current;
    private Point2D[] points;
    

    public BezierPanel()
    {
        Point2D p1= new Point2D.Double(64,190);
        Point2D p2= new Point2D.Double(64,150);
        Point2D p3= new Point2D.Double(64,104);
        Point2D p4= new Point2D.Double(64, 64);
        points = new Point2D[]{p1,p2,p3,p4};
        
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent event)
            {
                Point2D p =event.getPoint();
                for(int i = 0; i < points.length; i++)
                {
                    double x = points[i].getX() - SIZE/2;
                    double y = points[i].getY() - SIZE/2;
                    Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                    if(r.contains(p))
                    {
                        current = i;
                        break;
                    }
                }
            }
            public void mouseReleased(MouseEvent event)
            {
                current = -1;
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent event)
            {
                if (current == -1 ) {
                    return;
                }
                if(current != -1)
                    points[current] = event.getPoint();
                
                repaint();
            }
        });
        current = -1;
    }
    public Point2D cubicBezier(double t, Point2D[] p)
    {
        Point2D[] temp = new Point2D[p.length];
        for(int k=0; k < p.length; k++)
            temp[k]=p[k];
        for(int i=0; i< 3; i++)
        {
            for(int j = 0; j < 4-i-1 ; j++)
            {
                double x = (1-t)*temp[j].getX() + t*temp[j+1].getX();
                double y = (1-t)*temp[j].getY()+ t*temp[j+1].getY();
                temp[j] = new Point2D.Double(x,y);
            }
        }
        return temp[0];
    }
    
    public void drawBezier(Graphics g, Point2D[] p)
    {
        for(double t = 0; t < 1; t+=0.002)
        {
            Point2D p1= cubicBezier(t,p);
            Point2D p2 = cubicBezier(t+0.001,p);
            g.drawLine((int)Math.round(p1.getX()),(int)Math.round(p1.getY()),(int)Math.round(p2.getX()),(int)Math.round(p2.getY()));
        }
    }
    public void paintComponent(Graphics g)
    {
        if(points == null) return;
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < points.length; i++)
        {
            double x = points[i].getX() - SIZE/2;
            double y = points[i].getY() - SIZE/2;
            g2.drawString(String.valueOf(i+1), (float)(x+SIZE), (float)(y+SIZE));
            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }
        drawBezier(g,points);
    }
}