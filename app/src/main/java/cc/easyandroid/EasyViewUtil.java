package cc.easyandroid;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EasyViewUtil {

	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(View view, int id) {
		return (T) view.findViewById(id);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}

	// ------------------我是分割线
	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(View view, int id, OnClickListener onClickListener) {
		T t = (T) view.findViewById(id);
		if (onClickListener != null) {
			t.setOnClickListener(onClickListener);
		}
		return t;
	} 

	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(Activity activity, int id, OnClickListener onClickListener) {
		T t = (T) activity.findViewById(id);
		if (onClickListener != null) {
			t.setOnClickListener(onClickListener);
		}
		return t;
	}

	// ------------------我是分割线----------------------listview设置item点击事件
	@SuppressWarnings("unchecked")
	public static <T extends ListView> T findViewById(View view, int id, OnItemClickListener onItemClickListener) {
		T t = (T) view.findViewById(id);
		if (onItemClickListener != null) {
			t.setOnItemClickListener(onItemClickListener);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public static <T extends ListView> T findViewById(Activity activity, int id, OnItemClickListener onItemClickListener) {
		T t = (T) activity.findViewById(id);
		if (onItemClickListener != null) {
			t.setOnItemClickListener(onItemClickListener);
		}
		return t;
	}
	// ------------------我是分割线----------------------dialog
	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(Dialog dialog, int id) {
		return (T) dialog.findViewById(id);
	}
}
