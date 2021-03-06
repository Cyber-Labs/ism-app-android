package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model;

public class Member {
    private final String name;
    private final boolean is_admin;
    private final String email;

    public Member(String name, boolean is_admin, String email) {
        this.name = name;
        this.is_admin = is_admin;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public String getEmail() {
        return email;
    }
}
