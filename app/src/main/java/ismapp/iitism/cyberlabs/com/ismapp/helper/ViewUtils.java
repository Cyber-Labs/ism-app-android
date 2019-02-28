package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;
import android.widget.Toast;

public class ViewUtils {
    public static void showToast(Context context , String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
