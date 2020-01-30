package com.example.androidtask.APIs.wsutils;

import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidtask.APIs.CustomVolleyFiles.MyVolley;
import com.example.androidtask.APIs.interfaces.IParsePostListener;
import com.example.androidtask.APIs.utils.StaticUtils;

import org.json.JSONObject;




public class JSONRequestResponse {

    public JSONRequestResponse() {

    }
    private int reqCode;
    private static final String TAG = "APP_REQUEST";


    public void getResponseObject(String url, final int requestCode, IParsePostListener mParseListener, Bundle params) {
        getResponseObject(url, TAG, requestCode, mParseListener);
    }




    private void getResponseObject(String url, String tag, final int requestCode, final IParsePostListener mParseListener) {
        this.reqCode = requestCode;

        Response.Listener<JSONObject> sListener = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (mParseListener != null) {
                    mParseListener.SuccessResponse(response.toString(), reqCode);
                }
            }
        };

        Response.ErrorListener eListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (mParseListener != null) {
                    mParseListener.ErrorResponse(error, reqCode);
                }
            }
        };

        int timeOut = StaticUtils.TIME_OUT;
        boolean isFile = false;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, sListener, eListener);
        if (tag != null)
            jsObjRequest.setTag(tag);
        else
            jsObjRequest.setTag(TAG);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getRequestQueue().add(jsObjRequest);
    }





}
