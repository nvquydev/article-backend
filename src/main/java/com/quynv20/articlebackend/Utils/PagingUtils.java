package com.quynv20.articlebackend.Utils;

import java.util.ArrayList;
import java.util.List;

public class PagingUtils {

    public static <T> List<T> applyPaging(int pageNumber, int pageSize, List<T> data) {
        if (data != null && pageNumber >= 0 && pageSize > 0) {
            // Apply paging to return data for user.
            int fromIndex = pageNumber * pageSize;
            int toIndex = (pageNumber + 1) * pageSize;
            int total = data.size();
            if (toIndex > total) {
                toIndex = total;
            }

            // Validate again for fromIndex, toIndex, it should be smaller than total
            if (fromIndex <= toIndex && toIndex <= total) {
                List<T> subListForPage = data.subList(fromIndex, toIndex);
                List<T> dataForPage = new ArrayList<>(subListForPage);
                return dataForPage;
            }
        }
        return null;
    }

}

