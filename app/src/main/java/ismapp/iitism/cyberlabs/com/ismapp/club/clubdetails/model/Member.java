package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model;

public class Member {
    private String name;
    private boolean is_admin;

    public Member(String name, boolean is_admin) {
        this.name = name;
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
