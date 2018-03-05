package cc.easyandroid;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class EasyToast {

	private static boolean isShow = true;
	static Toast toast;

	private static void deliverToast(Context context, int text, int duration) {
		if (context != null) {
			if (toast == null) {
				toast = Toast.makeText(context.getApplicationContext(), text, duration);
			} else {
				toast.setText(text);
			}
			toast.show();
		}
	}

	public static void show(Context context, int text, int duration) {
		if (Looper.myLooper() != Looper.getMainLooper()) {
			Looper.prepare();
			deliverToast(context, text, duration);
			Looper.loop();
			return;
		} else {
			deliverToast(context, text, duration);
		}
	}

	private static void deliverToast(Context context, CharSequence text, int duration) {
		if (context != null) {
			if (toast == null) {
				toast = Toast.makeText(context.getApplicationContext(), text, duration);
			} else {
				toast.setText(text);
			}
			toast.show();
		}
	}

	public static void show(Context context, CharSequence text, int duration) {
		if (Looper.myLooper() != Looper.getMainLooper()) {
			Looper.prepare();
			deliverToast(context, text, duration);
			Looper.loop();
			return;
		} else {
			deliverToast(context, text, duration);
		}
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow)
			show(context, message, Toast.LENGTH_SHORT);
	}

	public static void showShort(Context context, int message) {
		if (isShow)
			show(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow)
			show(context, message, Toast.LENGTH_LONG);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow)
			show(context, message, Toast.LENGTH_LONG);
	}

	@SuppressWarnings("unused")
	private void throwIfNotOnMainThread() {
		if (Looper.myLooper() != Looper.getMainLooper()) {
			throw new IllegalStateException("Toast must be invoked from the main thread.");
		}
	}
}
