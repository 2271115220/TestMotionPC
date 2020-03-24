package cn.jianke.socket.tcp;

import cn.jianke.socket.tcp.bean.Action;

import java.awt.*;
import java.awt.event.InputEvent;
import java.text.NumberFormat;
import java.util.List;

public class MyMouseController {

    private Dimension dim; //存储屏幕尺寸
    private Robot robot;//自动化对象

    private double pcWidth;
    private double pcHeight;

    private int lastX;
    private int lastY;
    private NumberFormat numberFormat;

    private Bezier mBezier;

    public MyMouseController() {
        numberFormat = NumberFormat.getInstance();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        pcWidth = dim.getWidth();
        pcHeight = dim.getHeight();
        mBezier = new Bezier();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void click(Action action) {    //鼠标点击
        float appX = action.getAppWidth();
        float appY = action.getAppHeight();
        lastX = (int) (pcWidth / appX * action.getX());
        lastY = (int) (pcHeight / appY * action.getY());
        try {
            robot.mouseMove(lastX, lastY);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MoveToPosition(Action action) {    //鼠标移动函数
        float appX = action.getAppWidth();
        float appY = action.getAppHeight();
        int x = (int) (pcWidth / appX * action.getX());
        int y = (int) (pcHeight / appY * action.getY());

        Point startPoint = new Point(lastX, lastY);
        Point endPoint = new Point((lastX + x) / 2, (lastY + y) / 2);

        List<Point> pointList = mBezier.bezier(startPoint, startPoint, endPoint);
        Point lastPoint = startPoint;
        try {
            for (Point point : pointList) {
                robot.mouseMove(point.x, point.y);
//                if (lastPoint != null) {
//                    if (point.x != lastPoint.x || point.y != lastPoint.y) {
//                        robot.mouseMove(point.x, point.y);
//                        lastPoint = point;
//                    }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastX = x;
        lastY = y;
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }


    public static void main(String args[]) throws Exception {

        MyMouseController mmc = new MyMouseController();
        mmc.lastX = 0;
        mmc.lastY = 0;
        Action action = new Action();
        action.setAppHeight(1080);
        action.setAppWidth(2340);
        action.setX((float) mmc.pcWidth);
        action.setY((float) mmc.pcHeight);
        mmc.MoveToPosition(action);

    }
}