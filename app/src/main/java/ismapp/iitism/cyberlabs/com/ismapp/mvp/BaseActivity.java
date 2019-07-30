package ismapp.iitism.cyberlabs.com.ismapp.mvp;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar, null)).setCancelable(false).create();
    }

    public void showProgressBar(boolean show) {
        if (show) alertDialog.show();
        else alertDialog.dismiss();
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isConnected();
    }

    public void showConnectionFailureDialog() {


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_coon);
        Button btn = dialog.findViewById(R.id.dialog_button);
        TextView rules5 = dialog.findViewById(R.id.rules5);
        btn.setText(R.string.retry);
        rules5.setText(R.string.no_internet_connection);
        dialog.setTitle("Connection Failed");
        dialog.setCancelable(false);
        dialog.show();
        btn.setOnClickListener(v -> {
            callPresenter();
            dialog.dismiss();
        });

    }

    protected abstract void callPresenter();

    protected boolean emailInvalid(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean a = matcher.matches();
        return !a;
    }
    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void showError(String message) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

}
