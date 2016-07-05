package org.pma.nutrifami.data;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by juras on 13-Jun-16.
 */

public class GsonRequest<T> extends Request<GsonContainer<T>> {
    private final Class<T> mClass;
    private final Response.Listener<GsonContainer<T>> mListener;

    private static final String HEADER_ENCODING = "Content-Encoding";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ENCODING_GZIP = "gzip";

    public GsonRequest(String url, Class<T> clazz, Response.Listener<GsonContainer<T>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
    }

    @Override
    protected Response<GsonContainer<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = "";
            if (isGzipped(response)) {
                try {
                    byte[] data = decompressResponse(response.data);
                    json = new String(data, "UTF-8");
                } catch (IOException e) {
                    // it seems that result is not GZIP
                }
            } else {
                json = new String(
                        response.data,
                        "UTF-8"
                );
            }

            return Response.success(
                    new GsonContainer<T>(GsonUnitDeserializer.createGson().fromJson(json, mClass), json),
                    HttpHeaderParser.parseCacheHeaders(response)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isGzipped(NetworkResponse response) {
        final Map<String, String> headers = response.headers;
        return headers != null && !headers.isEmpty() && headers.containsKey(HEADER_ENCODING) &&
                headers.get(HEADER_ENCODING).equalsIgnoreCase(ENCODING_GZIP);
    }

    private byte[] decompressResponse(byte [] compressed) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            int size;
            final ByteArrayInputStream memoryStream = new ByteArrayInputStream(compressed);
            final GZIPInputStream gzip = new GZIPInputStream(memoryStream);
            final int buffSize = 8192;
            byte[] tempBuffer = new byte[buffSize];
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((size = gzip.read(tempBuffer, 0, buffSize)) != -1) {
                byteArrayOutputStream.write(tempBuffer, 0, size);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        final Map<String, String> beforeHeaders = super.getHeaders();
        final Map<String, String> headers = new HashMap<>();
        headers.putAll(beforeHeaders);
        headers.put(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
        return headers;
    }

    @Override
    protected void deliverResponse(GsonContainer<T> response) {
        this.mListener.onResponse(response);
    }
}
