package common;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询条件基类
 */
@Data
public class BaseQueryDto implements Serializable {

    private static final long serialVersionUID = -1489546971011268121L;
    /**
     * 页码
     * 第一页为1，默认值为1。
     */
    private long current = 1;
    /**
     * 每页数量
     * 默认值为20，不能超过1000，否则返回错误信息。
     */
    private long size = 20;

    /**
     * 查询顺序
     */
    private String orderBy;

}
