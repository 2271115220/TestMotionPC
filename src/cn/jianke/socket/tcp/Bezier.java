package cn.jianke.socket.tcp;

import cn.jianke.socket.tcp.bean.Action;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bezier {

    public List<Point> bezier(Point startPoint, Point cPoint, Point endPoint) {

        int steps = 10;
        //Point [] newPoints=new Point[steps+1];
        List<Point> list = new ArrayList<Point>();
        float tStep = 1 / ((float) steps);

        float t = 0f;
        for (int ik = 0; ik <= steps; ik++) {
            int x = (int) calculateQuadSpline(startPoint.x, cPoint.x, endPoint.x, t);
            int y = (int) calculateQuadSpline(startPoint.y, cPoint.y, endPoint.y, t);
            // newPoints[ik]=new Point(x,y);
            list.add(new Point(x, y));
            t = t + tStep;
        }
        return list;
    }

    private float calculateQuadSpline(float z0, float z1, float z2, float t) {
        float a1 = (float) ((1.0 - t) * (1.0 - t) * z0);
        float a2 = (float) (2.0 * t * (1 - t) * z1);
        float a3 = (float) (t * t * z2);
        float a4 = a1 + a2 + a3;
        return a4;
    }


    public static void main(String[] args) {
        Bezier bezier = new Bezier();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Point startPoint = new Point(100, 100);
        Point cPoint = new Point(100, 100);
        Point endPoint = new Point(600, 1400);
        List<Point> bezierL = bezier.bezier(startPoint, cPoint, endPoint);
        for (int i = 0; i < bezierL.size(); i++) {
            robot.mouseMove((int) bezierL.get(i).getX(), (int) bezierL.get(i).getY());
            robot.delay(500);
            cPoint = new Point((int) bezierL.get(i).getX(), (int) bezierL.get(i).getY());
        }
//        bezier.test();
    }
}
