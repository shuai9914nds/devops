package com.devops.base.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.ResultPagedData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BeanConverter<T, R> {
    private static final Logger logger = LoggerFactory.getLogger(BeanConverter.class);

    public static <T, R> R convert(T source, Class<R> clazz) {
        if (source == null) {
            return null;
        }
        R target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("bean转换失败", e);
        }
        return target;
    }

    public static <T, R> List<R> convertList(List<T> source, Class<R> clazz) {
        return source.stream().map(i -> convert(i, clazz)).collect(Collectors.toList());
    }

    public static <T, R> Page<R> convertPageData(Page<T> page, Class<R> clazz) {
        Page<R> resp = new Page<>();
        resp.setRecords(page.getRecords().stream().map(i -> convert(i, clazz)).collect(Collectors.toList()));
        resp.setCurrent(page.getCurrent());
        resp.setSize(page.getSize());
        resp.setTotal(page.getTotal());
        resp.setPages(page.getPages());
        return resp;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> Obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                log.error("object转化为map异常，e={}", e.getMessage());
            }
        }
        return map;
    }

    //不做数据转换，手动转换
    public static <T, R> ResultPagedData<R> convertPagedDataWithoutData(ResultPagedData<T> resultPagedData, Class<R> clazz) {
        ResultPagedData<R> resp = new ResultPagedData<>();
        resp.setDatas(new ArrayList<>());
        resp.setPageIndex(resultPagedData.getPageIndex());
        resp.setPageSize(resultPagedData.getPageSize());
        resp.setTotalCount(resultPagedData.getTotalCount());
        resp.setTotalPage(resultPagedData.getTotalPage());
        return resp;
    }

}
