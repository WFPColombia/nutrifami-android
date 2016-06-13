package org.pma.nutrifami.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by juras on 13-Jun-16.
 */

public class FileReadTask extends AsyncTask<String, Object, String> {
    private final Context mContext;
    private final OnFileReadListener mFileReadListener;

    public FileReadTask(Context context, OnFileReadListener fileReadListener) {
        this.mContext = context;
        this.mFileReadListener = fileReadListener;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        try {
            final InputStream inputStream = this.mContext.openFileInput(params[0]);
            if ( inputStream != null ) {
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                final StringBuilder stringBuilder = new StringBuilder();
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("FileReadTask", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("FileReadTask", "Can not read file: " + e.toString());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        this.mFileReadListener.onFileRead(result);
    }
}
