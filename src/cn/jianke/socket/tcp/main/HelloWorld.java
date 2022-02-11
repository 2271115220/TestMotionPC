package cn.jianke.socket.tcp.main;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        List<GridBean.ResultBean> gridBeans = new ArrayList<>();
        List<BaseGridItemBean> gridItemBeans = new ArrayList<>();

        for (int i = 0; i < gridBeans.size(); i++) {
            if (i == 0) {
                BaseGridItemBean gridItemBean = new BaseGridItemBean();
                gridItemBean.setTitle(gridBeans.get(0).getTitle());
                gridItemBean.setType("title");
                gridItemBeans.add(gridItemBean);
                gridItemBean = null;
            } else {
                if (!gridBeans.get(i).getTitle().equals(gridBeans.get(i - 1).getTitle())) {
                    BaseGridItemBean gridItemBean = new BaseGridItemBean();
                    gridItemBean.setTitle(gridBeans.get(i).getTitle());
                    gridItemBean.setType("title");
                    gridItemBeans.add(gridItemBean);

                    gridItemBean = null;
                } else {
                    BaseGridItemBean gridItemBean = new BaseGridItemBean();
                    gridItemBean.setObject(gridBeans.get(i));
                    gridItemBean.setType("content");
                    gridItemBeans.add(gridItemBean);
                    gridItemBean = null;
                }
            }
        }
    }
}
