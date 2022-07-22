package Entity;

import org.postgresql.shaded.com.ongres.stringprep.StringPrep;

public class User {
    private long id;
    private long nationalCode;
    private String birthday;
    private String password;

    public User(long id, long nationalCode, String birthday, String password) {
        this.id = id;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Entity.User{" +
                "id=" + id +
                ", nationalCode=" + nationalCode +
                ", birthday='" + birthday + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
