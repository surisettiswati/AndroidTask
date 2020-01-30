
package com.example.androidtask.APIs.CustomVolleyFiles;

import android.util.Log;

import java.util.Locale;

class FLog
{
	private static final String TAG = "Volley";
	public static void e(String format, Object... args)
	{
		Log.e(TAG, buildMessage(format, args));
	}
	/**
	 * Formats the caller's provided message and prepends useful info like
	 * calling thread ID and method name.
	 */
	private static String buildMessage(String format, Object... args)
	{
		String msg = (args == null) ? format : String.format(Locale.US, format, args);
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

		String caller = "<unknown>";
		// Walk up the stack looking for the first caller outside of FLog.
		// It will be at least two frames up, so start there.
		for (int i = 2; i < trace.length; i++)
		{
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(FLog.class))
			{
				String callingClass = trace[i].getClassName();
				callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
				callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

				caller = callingClass + "." + trace[i].getMethodName();
				break;
			}
		}
		return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
	}



}
