package com.example.dayoungkim.mundan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateLikeRequest extends StringRequest {

    final static private String URL = "http://115.68.222.192/like_check2.php";
    private Map<String, String> parameters;

    public ValidateLikeRequest(String like_id, String post_idx, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("like_id", like_id);
        parameters.put("post_idx", post_idx);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
