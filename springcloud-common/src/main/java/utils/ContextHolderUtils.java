package utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author: liushuai
 * @date: 2020/11/21
 * @description：
 */
public class ContextHolderUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

}
