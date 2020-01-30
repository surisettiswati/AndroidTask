package com.example.androidtask.APIs.CustomVolleyFiles;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Helper class that is used to provide references to initialized
 * RequestQueue(s) and ImageLoader(s)
 * 
 */
public class MyVolley
{
	private static RequestQueue mRequestQueue;

	private MyVolley()
	{
		// no instances
	}

	public static void init(Context context)
	{
		//mRequestQueue = Volley.newRequestQueue(context);
		initVolley(context);

		int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		// Use 1/8th of the available memory for this memory cache.
		int cacheSize = 1024 * 1024 * memClass / 8;
	}

	public static RequestQueue getRequestQueue()
	{
		if (mRequestQueue != null)
		{
			return mRequestQueue;
		}
		else
		{
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

	/**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means
	 * that no memory caching is used. This is useful for images that you know
	 * that will be show
	 * only once.
	 *
	 */

	private static void initVolley(Context context)
	{
		SSLContext sslContext = null;
		try
		{
			TrustManager[] trustManager = new TrustManager[]{new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				@SuppressLint("TrustAllX509TrustManager")
				@Override
				public void checkServerTrusted(X509Certificate[] chain,
											   String authType) {
				}

				@SuppressLint("TrustAllX509TrustManager")
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
											   String authType) {
				}
			}};

			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustManager, new SecureRandom());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (KeyManagementException e)
		{
			e.printStackTrace();
		}

		if (sslContext != null)
			mRequestQueue = Volley.newRequestQueue(context, new HurlStack(null,
				sslContext.getSocketFactory()));
		else
			mRequestQueue = Volley.newRequestQueue(context);
	}
}
