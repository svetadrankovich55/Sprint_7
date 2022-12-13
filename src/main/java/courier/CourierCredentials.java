package courier;

public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public static CourierCredentials fromWithEmptyLogin(Courier courier) {
        return new CourierCredentials("", courier.getPassword());
    }

    public static CourierCredentials fromWithEmptyPassword(Courier courier) {
        return new CourierCredentials(courier.getLogin(),"");
    }

    public static CourierCredentials fromWithIncorrectLogin(Courier courier) {
        return new CourierCredentials("Uchiha123456", courier.getPassword());
    }

    public static CourierCredentials fromWithIncorrectPassword(Courier courier) {
        return new CourierCredentials(courier.getLogin(), "P@ssw0rd456");
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

}
