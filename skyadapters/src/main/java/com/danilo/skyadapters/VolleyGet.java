package com.danilo.skyadapters;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danilo.skyadapters.recycler.RvActivity;


/**
 * Created by ttlnisoffice on 1/30/18.
 */

public class VolleyGet implements Response.Listener<String>, Response.ErrorListener {

    private RvActivity a;
    private OnResponse onResponse;

    public VolleyGet(RvActivity a, String url, OnResponse onResponse) {
        this.a = a;
        this.onResponse = onResponse;
        Volley.newRequestQueue(a).add(new StringRequest(Request.Method.GET, url, this, this));
    }

    @Override
    public void onResponse(String response) {
        onResponse.parseData(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("VolleyGet - Error:", error.getLocalizedMessage());
    }

    public interface OnResponse {
        public void parseData(String response);
    }
}
