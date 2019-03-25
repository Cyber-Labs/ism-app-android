package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {
    public static int getAttrColor(Context context, int attrId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();

        theme.resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

}
