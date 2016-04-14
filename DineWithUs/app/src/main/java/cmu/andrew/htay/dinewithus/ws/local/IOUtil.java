package cmu.andrew.htay.dinewithus.ws.local;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by HuiJun on 4/6/16.
 */
public class IOUtil {

    public static void logFile(Context context, String filename, String error) {
        Calendar time = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String timeStamp = dateFormat.format(time.getTime());


        StringBuilder sb = new StringBuilder();
        sb.append(timeStamp);
        sb.append(": ");
        sb.append(error);
        sb.append("\n");
        String errorMessage = sb.toString();

        try {
            File myFile = new File(context.getFilesDir(), filename);
            myFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(myFile, true);
            OutputStreamWriter out =
                    new OutputStreamWriter(fileOut);
            System.out.println("Writing error - " + errorMessage);
            out.append(errorMessage);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);

        }
    }
}
