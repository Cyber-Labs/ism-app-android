package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model;

import java.util.List;

public class MemberListResponse {
    private boolean success;
    private String message;
    private List<Member> member_list;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Member> getMember_list() {
        return member_list;
    }
}
