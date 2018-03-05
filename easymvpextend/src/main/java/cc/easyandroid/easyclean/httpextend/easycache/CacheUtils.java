package cc.easyandroid.easyclean.httpextend.easycache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CacheUtils {
	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static byte[] bitmap2Bytes(Bitmap bm) {
		if (bm == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static Bitmap bytes2Bitmap(byte[] bytes) {
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * Drawable → Bitmap
	 */
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		if (drawable == null) {
			return null;
		}
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/*
	 * Bitmap → Drawable
	 */
	@SuppressWarnings("deprecation")
	public static Drawable bitmap2Drawable(Bitmap bm) {
		if (bm == null) {
			return null;
		}
		BitmapDrawable bd = new BitmapDrawable(bm);
		bd.setTargetDensity(bm.getDensity());
		return new BitmapDrawable(bm);
	}

	// =======================================
	// ============== 序列化 数据 读写 =============
	// =======================================

	public static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
			File cacheFile=context.getExternalCacheDir();
			if(cacheFile!=null&&!cacheFile.exists()){
				cacheFile.mkdirs();
			}
			System.out.println("cacheFile="+cacheFile);
			if(cacheFile!=null){
				cachePath = cacheFile.getPath();
			}else{
				cachePath = context.getCacheDir().getPath();
			}
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		File fileDir=new File(cachePath + File.separator + uniqueName);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		return fileDir;
	}
}