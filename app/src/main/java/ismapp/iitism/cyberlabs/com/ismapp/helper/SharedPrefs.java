package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private static String TAG = "Shared Preference";
    private  final String PREF_NAME = "Login";

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;



    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {

        return  "Token " + pref.getString(KEY_ACCESS_TOKEN, null);
    }
}
