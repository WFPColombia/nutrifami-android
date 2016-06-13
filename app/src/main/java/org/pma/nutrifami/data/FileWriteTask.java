package org.pma.nutrifami.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.R.attr.data;

/**
 * Created by juras on 13-Jun-16.
 */

public class FileWriteTask extends AsyncTask<String, Object, Boolean> {
    private final OnFileWrittenListener mFileWrittenListener;
    private final Context mContext;

    public FileWriteTask(Context context, OnFileWrittenListener onFileWrittenListener) {
        this.mContext = context;
        this.mFileWrittenListener = onFileWrittenListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mContext.openFileOutput(params[0], Context.MODE_PRIVATE));
            outputStreamWriter.write(params[1]);
            outputStreamWriter.close();
            return true;
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (this.mFileWrittenListener != null) {
            this.mFileWrittenListener.onFileWritten(result);
        }
    }
}
