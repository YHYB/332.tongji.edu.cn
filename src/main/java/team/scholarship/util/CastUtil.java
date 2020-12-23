package team.scholarship.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CastUtil
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/23 20:02
 */
public class CastUtil {

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;

    }
}
