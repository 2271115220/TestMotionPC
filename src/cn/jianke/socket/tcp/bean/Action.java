package cn.jianke.socket.tcp.bean;

/**
 * @author wfx: 一行代码,亿万生活.
 * @date 2020/3/23 15:06
 * @desc
 */
public class Action {


    /**
     * action : down  手势动作
     * x : 1    动作为move     在x轴上的偏移量(相比上一点) ，单位：%
     * y : 1    动作为move     在y轴上的偏移量(相比上一点)，单位：%
     * x : 1    动作为down     在x轴上的偏移量(相比原点) ，单位：%
     * y : 1    动作为down     在y轴上的偏移量(相比原点)，单位：%
     */

    private int action;
    private String x;
    private String y;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
