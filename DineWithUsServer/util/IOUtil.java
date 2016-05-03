package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by HuiJun on 4/6/16.
 */
public class IOUtil {

    public static void logFile(String error, String filename) {
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
            File myFile = new File(filename);
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
