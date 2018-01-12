package sample.Models;

public class User {

    private String name,surname,login,password, data_ur, adres;
    private boolean admin;
    private int id, pesel;

    public User(String name, String surname, String login, String password,int id,boolean admin, int pesel, String data_ur, String adres) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.id=id;
        this.admin=admin;
        this.pesel=pesel;
        this.data_ur=data_ur;
        this.adres=adres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_ur() { return data_ur; }

    public String getAdres() { return adres; }

    public int getPesel() { return pesel; }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
