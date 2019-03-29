package is.hi.hbv601g.jollytime.Services;

public class UserService {

    private String email;
    private String name;
    private String password1;
    private String password2;


    public UserService(String email, String name, String password1, String password2) {
        this.email = email;
        this.name = name;
        this.password1 = password1;
        this.password2 = password2;
    }

    // Skilar true ef lykilorð eru eins
    // Annars false
    public Boolean doPasswordsMatch() {
        return password1.equals(password2);
    }

    // Skilar true ef name er tómt
    // annars false
    public Boolean isNameEmpty() {
        return name.isEmpty();
    }

    // Skilar true ef email er tómt
    // annars false
    public Boolean isEmailEmpty() {
        return email.isEmpty();
    }

    public Boolean isEitherPasswordEmpty() {
        return password1.isEmpty() || password2.isEmpty();
    }
}
