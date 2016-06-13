package org.pma.nutrifami.data;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by juras on 13-Jun-16.
 */

public class GsonRequest<T> extends Request<GsonContainer<T>> {
    private final Class<T> mClass;
    private final Response.Listener<GsonContainer<T>> mListener;

    public GsonRequest(String url, Class<T> clazz, Response.Listener<GsonContainer<T>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
    }

    @Override
    protected Response<GsonContainer<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                response.data,
                "UTF-8"
            );
            return Response.success(
                    new GsonContainer<T>(GsonUnitDeserializer.createGson().fromJson(json, mClass), json),
                    HttpHeaderParser.parseCacheHeaders(response)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(GsonContainer<T> response) {
        this.mListener.onResponse(response);
    }
}
