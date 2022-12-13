import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;


public class LoginCourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();
    private final CourierAssertions courierAssertions = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = courierClient.delete(courierId);
          courierAssertions.successfulDelete(response);
        }
    }

    @Test
    @DisplayName("Successful login courier")
    public void successfulLoginCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierId = courierAssertions.successfulLogin(loginResponse);

    }

   @Test
   @DisplayName("Failed login courier with empty login")
    public void failedLoginCourierWithEmptyLogin() {
       Courier courier = generator.random();
       ValidatableResponse creationResponse = courierClient.create(courier);
       courierAssertions.successfulCreation(creationResponse);

       CourierCredentials courierCredentials = CourierCredentials.fromWithEmptyLogin(courier);
       ValidatableResponse loginResponse = courierClient.login(courierCredentials);
       courierAssertions.failedCourierLoginWithEmptyLoginOrPassword(loginResponse);
   }

    @Test
    @DisplayName("Failed login courier with empty password")
    public void failedLoginCourierWithEmptyPassword() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);

        CourierCredentials courierCredentials = CourierCredentials.fromWithEmptyPassword(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierAssertions.failedCourierLoginWithEmptyLoginOrPassword(loginResponse);
    }

    @Test
    @DisplayName("Failed login courier with incorrect login")
    public void failedLoginCourierWithIncorrectLogin() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);

        CourierCredentials courierCredentials = CourierCredentials.fromWithIncorrectLogin(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierAssertions.failedCourierLoginWithIncorrectLoginOrPassword(loginResponse);
    }

    @Test
    @DisplayName("Failed login courier with incorrect password")
    public void failedLoginCourierWithIncorrectPassword() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);

        CourierCredentials courierCredentials = CourierCredentials.fromWithIncorrectPassword(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierAssertions.failedCourierLoginWithIncorrectLoginOrPassword(loginResponse);
    }
}
