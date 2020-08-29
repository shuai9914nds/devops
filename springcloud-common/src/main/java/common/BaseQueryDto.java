package common;

import java.io.Serializable;

/**
 * 查询条件基类
 */
public class BaseQueryDto implements Serializable {

    private static final long serialVersionUID = -1489546971011268121L;
    /**
     * 页码
     * 第一页为1，默认值为1。
     */
    private Integer pageIndex = 1;
    /**
     * 每页数量
     * 默认值为20，不能超过1000，否则返回错误信息。
     */
    private Integer pageSize = 20;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 自定义公共方法
     *
     * @param data 详见{@link BaseQueryDto}
     * @return String
     */
    public static String validator(BaseQueryDto data) {
        String result = "";
        if (data == null) {
            return "查询条件不能为空";
        }
        if (data.getPageSize() == null || data.getPageIndex() == null) {
            return "参数错误，页码和每页数量不能为空";
        }

        if (data.getPageIndex() < 1) {
            data.setPageIndex(1);
        }
        if (data.getPageSize() > 1000) {
            return "每页数量不能超过1000";
        }

        return result;
    }
}
