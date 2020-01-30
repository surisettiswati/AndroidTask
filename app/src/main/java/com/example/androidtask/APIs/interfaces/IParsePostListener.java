package com.example.androidtask.APIs.interfaces;

import com.android.volley.VolleyError;

public interface IParsePostListener {


	void ErrorResponse(VolleyError error, int requestCode);

	void SuccessResponse(String response, int requestCode);

}
