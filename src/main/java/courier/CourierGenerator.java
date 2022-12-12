package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public Courier generic() {
        return new Courier("Uchiha", "P@ssw0rd123", "Saske");
    }

    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "P@ssw0rd123", "Saske");
    }

    public Courier genericCourierWithEmptyLogin() {
        return new Courier("", "P@ssw0rd123", "Saske");
    }

    public Courier genericCourierWithEmptyPassword() {
        return new Courier("Uchiha", "", "Saske");
    }

    public Courier genericCourierWithIncorrectLogin() {
        return new Courier("Uchiha123", "P@ssw0rd123", "Saske");
    }

    public Courier genericCourierWithIncorrectPassword() {
        return new Courier("Uchiha", "P@ssw0rd456", "Saske");
    }

}
