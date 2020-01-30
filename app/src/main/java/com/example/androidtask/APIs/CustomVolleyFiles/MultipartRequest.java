package com.example.androidtask.APIs.CustomVolleyFiles;



import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

class MultipartRequest extends Request<JSONObject> {

    private MultipartEntity entity = new MultipartEntity();

    private final Response.Listener<JSONObject> mListener;

    private final HashMap<String, File> mAttachFileList;



    public MultipartRequest(String url, Response.ErrorListener errorListener,
                            Response.Listener<JSONObject> listener,
                            Bundle parameters,
                            HashMap<String, File> mAttachFileList2) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mAttachFileList = mAttachFileList2;
        buildMultipartEntity(parameters);
    }


    private void buildMultipartEntity(Bundle parameters) {
        entity = encodePOSTUrl(entity, parameters);

        for (String file_key : mAttachFileList.keySet()) {
            File mFile = mAttachFileList.get(file_key);
            entity.addPart(file_key, new FileBody(mFile));
        }
    }

    private static MultipartEntity encodePOSTUrl(MultipartEntity mEntity,
                                                 Bundle parameters) {
        if (parameters != null && parameters.size() > 0) {
            boolean first = true;
            for (String key : parameters.keySet()) {
                if (key != null) {
                    if (first) {
                        first = false;
                    }
                    String value = "";
                    Object object = parameters.get(key);
                    if (object != null) {
                        value = String.valueOf(object);
                    }
                    try {
                        mEntity.addPart(key, new StringBody(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mEntity;
    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            FLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            try {
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        try {
            mListener.onResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
