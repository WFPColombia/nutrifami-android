package org.pma.nutrifami.data;

/**
 * Created by juras on 13-Jun-16.
 */

public class GsonContainer<T> {
    private T mData;
    private String mJson;

    public GsonContainer(T data, String json) {
        this.mData = data;
        this.mJson = json;
    }

    public T getData() {
        return this.mData;
    }

    public String getJson() {
        return this.mJson;
    }
}
