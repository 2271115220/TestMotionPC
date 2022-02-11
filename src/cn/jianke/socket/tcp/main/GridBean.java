package cn.jianke.socket.tcp.main;

import java.util.List;

/**
 * @author zhd: 好好写
 * @date 2020/4/20 14:09
 * @desc
 */
public class GridBean {

    private int state;
    private String msg;
    private List<ResultBean> result;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        //小标题
        private String title;

        //此模块名称
        private String name;
        //此模块类型 1  来自server    0来自本地
        private String fromType;

        //加载的是web还是本地原生 0 本地，    1web
        private String loadType;

        //点击之后跳转的url
        private String url;
        //该模块封面
        private int tempLocal;//加载本地封面
        private String tempServer;//加载网络封面

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFromType() {
            return fromType;
        }

        public void setFromType(String fromType) {
            this.fromType = fromType;
        }

        public String getLoadType() {
            return loadType;
        }

        public void setLoadType(String loadType) {
            this.loadType = loadType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTempLocal() {
            return tempLocal;
        }

        public void setTempLocal(int tempLocal) {
            this.tempLocal = tempLocal;
        }

        public String getTempServer() {
            return tempServer;
        }

        public void setTempServer(String tempServer) {
            this.tempServer = tempServer;
        }
    }
}
