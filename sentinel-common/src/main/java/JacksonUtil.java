import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * 功能描述	jackson工具类
 * 创建作者	caizhiqin
 * 创建时间	2016年9月12日 下午12:02:02
 */
public final class JacksonUtil {

	public static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 *
	 * @param jsonStr  详见{@link String}
	 * @param valueType  详见{@link Class}
	 * @param <T> 详见{@link T}
	 * @return T 详见{@link T}
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * json数组转List
	 * @param jsonStr 详见{@link String}
	 * @param valueTypeRef  详见{@link TypeReference}
	 * @param <T> 详见{@link T}
	 * @return T 详见{@link T}
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把JavaBean转换为json字符串
	 *
	 * @param object 详见{@link Object}
	 * @return String 详见{@link String}
	 */
	public static String toJSon(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
		}
		return null;
	}

}