package customer;

import java.time.LocalDate;

public class Customer {
    public Customer(long id, String userName, String emailAddress, LocalDate birthDate) {
        this.id = id;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' + ",\n" +
                "username='" + userName + '\'' + ",\n" +
                "email address='" + emailAddress + '\'' + ",\n" +
                "birth date=" + birthDate ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private long id;

    private String userName;

    private String emailAddress;

    private LocalDate birthDate;

}