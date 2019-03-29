package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;

public class Utils {
    public static int getAttrColor(Context context, int attrId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();

        theme.resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }
    public static void goToSettings(FragmentActivity fragmentActivity) {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + fragmentActivity.getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fragmentActivity.startActivity(myAppSettings);

    }

}
