package ismapp.iitism.cyberlabs.com.ismapp.helper;

public interface PresenterCallback <T> {

    void onSuccess(T t);
    void OnFailure(String msg);

}
