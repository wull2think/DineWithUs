package cmu.andrew.htay.dinewithus.intents;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import cmu.andrew.htay.dinewithus.ws.local.IOUtil;

/**
 * Created by HuiJun on 4/28/16.
 */
public class WebImage  extends AsyncTask<Void, Void, Void> {

    private Drawable image;
    private String url;
    private ImageView view;
    private Context context;


    public WebImage(ImageView view, String url, Context context) {
        this.view = view;
        this.url = url;
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            image = Drawable.createFromStream(is, "storeImage");
        } catch (Exception e) {
            String error = "Unable to get image from web - " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (image != null)
            view.setImageDrawable(image);
    }


}
