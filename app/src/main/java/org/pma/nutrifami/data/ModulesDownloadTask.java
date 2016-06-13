package org.pma.nutrifami.data;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.pma.nutrifami.Constants;
import org.pma.nutrifami.model.Module;

/**
 * Created by juras on 13-Jun-16.
 */

public class ModulesDownloadTask {
    public void download(Context context, Response.Listener<GsonContainer<Module[]>> successListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<Module[]> request = new GsonRequest<>(
                Constants.MODULES_URL,
                Module[].class,
                successListener,
                errorListener
        );
        queue.add(request);
    }
}
