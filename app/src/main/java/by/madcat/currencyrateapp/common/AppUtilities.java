package by.madcat.currencyrateapp.common;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;

import java.util.Date;

public class AppUtilities {

    public static final boolean isNetworkAvailableAndConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworcConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();

        return isNetworcConnected;
    }

    public static final String getDateFromInterval(int interval){
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, interval);

        return sdf.format(calendar.getTimeInMillis());
    }

}
