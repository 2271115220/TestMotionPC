package cn.jianke.socket.tcp.bean;

/**
 * @date 2020/3/23 15:06
 * @desc
 */
public class Action {


    private int action;
    private float x;
    private float y;

    private float appWidth;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAppWidth() {
        return appWidth;
    }

    public void setAppWidth(float appWidth) {
        this.appWidth = appWidth;
    }

    public float getAppHeight() {
        return appHeight;
    }

    public void setAppHeight(float appHeight) {
        this.appHeight = appHeight;
    }

    private float appHeight;
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }



}
