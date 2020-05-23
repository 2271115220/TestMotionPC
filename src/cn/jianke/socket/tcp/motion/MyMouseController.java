package cn.jianke.socket.tcp.motion;

import cn.jianke.socket.tcp.Bezier;
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

    private int mPreX;
    private int mPreY;
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
        float appX = 2265.0f;
        float appY = 1005.0f;
        mPreX = (int) (pcWidth / appX * action.getX());
        mPreY = (int) (pcHeight / appY * action.getY());
        try {
            robot.mouseMove(mPreX, mPreY);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MoveToPosition(Action action) {    //鼠标移动函数
        float appX = 2265.0f;
        float appY = 1005.0f;
        float aaa = action.getX();
        float bbb = action.getY();
        int x = mPreX + (int) (pcWidth / appX * aaa);
        int y = mPreY + (int) (pcHeight / appY * bbb);
        robot.mouseMove(x, y);
        mPreX = x;
        mPreY = y;
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }

    public void Test() {
        for (int i = 0; i < 1000; i++) {
            robot.mouseMove(i, i);
            robot.delay(5);
        }
    }

    public static void main(String args[]) throws Exception {

        MyMouseController mmc = new MyMouseController();
        mmc.Test();

    }
}