package cn.jianke.socket.tcp;

import cn.jianke.socket.tcp.bean.Action;
import jdk.nashorn.internal.codegen.ClassEmitter;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.AWTException;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class MyMouseController {

    private Dimension dim; //存储屏幕尺寸
    private Robot robot;//自动化对象

    private double pcWidth;
    private double pcHeight;

    private int lastX;
    private int lastY;
    private NumberFormat numberFormat;

    public MyMouseController() {
        numberFormat = NumberFormat.getInstance();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        pcWidth = dim.getWidth();
        pcHeight = dim.getHeight();
        getSize();
        System.out.println("屏幕大小为：" + pcWidth + " " + pcHeight);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void getSize() {

//
//        System.out.println("width:" + w + "英寸");
//        System.out.println("height:" + h + "英寸");
//
//        double big = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
//
//        System.out.println(big + "英寸");

    }

    /**
     *
     */
    public void Move(Action action) {    //鼠标点击
        float appX = action.getAppWidth();
        float appY = action.getAppHeight();
        lastX = (int) (pcWidth / appX * action.getX());
        lastY = (int) (pcHeight / appY * action.getY());
        try {
            robot.mouseMove(lastX, lastY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MoveToPosition(Action action) {    //鼠标移动函数
        float appX = action.getAppWidth();
        float appY = action.getAppHeight();
        int x = (int) (pcWidth / appX * action.getX());
        int y = (int) (pcHeight / appY * action.getY());
        int code = 5;
        try {
            if (x > 5 || y > 5) {
                robot.mouseMove(x, y);
            } else {
                robot.mouseMove((x + lastX) / 2, (y + lastY) / 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        lastX = x;
        lastY = y;
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }

    public void Move2(int width) {    //鼠标移动函数
        System.out.println("enter Move()...");
        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
        width += mousepoint.x;
        try {
            robot.mouseMove(width, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }


    public static void main(String args[]) throws Exception {

        MyMouseController mmc = new MyMouseController();


    }
}