package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String CLUB_ID = "club_id";
    private static final String IS_ADMIN = "IS_ADMIN";
    private static final String IS_LOGIN="is_login";
    private static String TAG = "Shared Preference";
    private  final String PREF_NAME = "Login";
    private static final String CLUB_NAME="club_name";



    // Shared Preferences
   private SharedPreferences pref;
   private SharedPreferences.Editor editor;
  private   Context _context;


    // Shared pref mode
   private int PRIVATE_MODE = 0;
    public void setLogin(boolean login)
    {
        editor.putBoolean(IS_LOGIN,login);
        editor.commit();

    }
    public boolean getLogin(){
        return pref.getBoolean(IS_LOGIN,false);
    }
    public void setIsAdmin(boolean is_admin)
    {
        editor.putBoolean(IS_ADMIN,is_admin);
        editor.commit();

    }
    public boolean getIsAdmin(){
        return pref.getBoolean(IS_ADMIN,false);
    }

public void setClubId(int club_id){
    editor.putInt(CLUB_ID,club_id);
    editor.commit();
}
public int getClubId(){
    return pref.getInt(CLUB_ID,0);
}
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
    public void setClubName(String clubName)
    {

        editor.putString(CLUB_NAME, clubName);
        editor.commit();
    }
    public  String getClubName() {
        return  pref.getString(CLUB_NAME,null);
    }


}
