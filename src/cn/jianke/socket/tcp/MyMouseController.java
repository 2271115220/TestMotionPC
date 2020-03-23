package cn.jianke.socket.tcp;

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

    private NumberFormat numberFormat;

    public MyMouseController() {
        numberFormat = NumberFormat.getInstance();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        pcWidth = dim.getWidth();
        pcHeight = dim.getHeight();
        System.out.println("屏幕大小为：" + pcWidth + " " + pcHeight);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param width
     * @param heigh
     */
    public void Move(String width, String heigh) {    //鼠标点击

        System.out.println("enter Move()...");
        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
        System.out.println("移动前坐标：" + mousepoint.x + " " + mousepoint.y);
        BigDecimal b1 = new BigDecimal(width);
        BigDecimal b2 = new BigDecimal(heigh);
        int x = (int) Double.parseDouble(b1.multiply(new BigDecimal(pcWidth)).toString());
        int y = (int) Double.parseDouble(b2.multiply(new BigDecimal(pcHeight)).toString());
        try {
            robot.mouseMove(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int laseX;
    private int laseY;
    public void MoveToPosition(String width, String heigh) {    //鼠标移动函数
        System.out.println("enter Move()...");
        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
        System.out.println("移动前坐标：" + mousepoint.x + " " + mousepoint.y);
        BigDecimal b1 = new BigDecimal(width);
        BigDecimal b2 = new BigDecimal(heigh);
        int x = (int) Double.parseDouble(b1.multiply(new BigDecimal(pcWidth)).toString());
        int y = (int) Double.parseDouble(b2.multiply(new BigDecimal(pcHeight)).toString());
//        width = (int) pcWidth * width + mousepoint.x;
//        heigh = (int) pcHeight * heigh + mousepoint.y;
        try {
            robot.mouseMove(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("移动后坐标：" + width + " " + heigh);
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