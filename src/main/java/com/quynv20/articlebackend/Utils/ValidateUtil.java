package com.quynv20.articlebackend.Utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateUtil {
    public static boolean isNotNullOrEmpty(Object object) {
        return !isNullOrEmpty(object);
    }

    public static boolean isNullOrEmpty(Object object) {
        boolean result = false;
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            if (((String) object).equalsIgnoreCase("")) {
                result = true;
            }
        }

        if (object instanceof List<?>) {
            if (((List<?>) object).size() == 0) {
                result = true;
            }
        }

        if (object instanceof Set<?>) {
            if (((Set<?>) object).size() == 0) {
                result = true;
            }
        }

        if (object instanceof Map<?, ?>) {
            if (((Map<?, ?>) object).size() == 0) {
                result = true;
            }
        }

        return result;
    }
}
