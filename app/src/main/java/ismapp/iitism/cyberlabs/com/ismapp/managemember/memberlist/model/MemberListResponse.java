package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model;

import java.util.List;




    public class MemberListResponse {
        public  boolean success;
        public String message;
        public List<Member> member_list;

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


