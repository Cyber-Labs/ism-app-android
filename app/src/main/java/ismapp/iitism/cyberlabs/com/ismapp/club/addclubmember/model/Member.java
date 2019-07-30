package ismapp.iitism.cyberlabs.com.ismapp.club.addclubmember.model;

public class Member {
    private final Boolean success;
    private final String message;

    public Member(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Member{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

}
