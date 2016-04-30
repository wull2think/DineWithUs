package cmu.andrew.htay.dinewithus.intents;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by HuiJun on 4/28/16.
 */
public class WebImage  extends AsyncTask<Void, Void, Void> {

    Drawable image;
    String url;
    ImageView view;


    public WebImage(ImageView view, String url) {
        this.view = view;
        this.url = url;

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            image = Drawable.createFromStream(is, "storeImage");
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (image != null)
            view.setImageDrawable(image);
    }


}
