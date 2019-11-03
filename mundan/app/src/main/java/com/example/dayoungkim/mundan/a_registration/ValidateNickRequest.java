package com.example.dayoungkim.mundan.a_registration;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateNickRequest extends StringRequest {

    final static private String URL = "http://115.68.222.192/user_nick_Validate.php";
    private Map<String, String> parameters;

    public ValidateNickRequest(String nick, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("nick", nick);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
