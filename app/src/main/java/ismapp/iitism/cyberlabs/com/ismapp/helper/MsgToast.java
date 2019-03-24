package ismapp.iitism.cyberlabs.com.ismapp.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

class MsgToast extends Toast {
    public MsgToast(Context context, String message) {
        super(context);
        this.message = message;
    }

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    private String message;
    private Context mtcx;
    public MsgToast(Context context) {
        super(context);
    }
    void setToast(String message){
        this.message = message;
        Toast.makeText(mtcx,message,Toast.LENGTH_LONG).show();
    }
}
