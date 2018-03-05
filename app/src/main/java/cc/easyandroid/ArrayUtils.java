package cc.easyandroid;

import android.util.SparseArray;

import java.util.List;
import java.util.Map;

public class ArrayUtils {

	public static <T> boolean isEmpty(List<T> array) {
		if (array == null || array.size() == 0)
			return true;
		else
			return false;
	}

	public static <T> boolean isEmpty(SparseArray<T> array) {
		if (array == null || array.size() == 0)
			return true;
		else
			return false;
	}

	public static <E, T> boolean isEmpty(Map<T, E> array) {
		if (array == null || array.size() == 0)
			return true;
		else
			return false;
	}
 
}
