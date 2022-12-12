import courier.Courier;
import courier.CourierAssertions;
import courier.CourierClient;
import courier.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();
    private final CourierAssertions courierAssertions = new CourierAssertions();


    @Test
    @DisplayName("Successful creation courier")
    public void successfulCreateCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);
    }

    @Test
    @DisplayName("Failed creation courier with empty login")
    public void failedCreateCourierWithEmptyLogin() {
        Courier courier = generator.genericCourierWithEmptyLogin();
        courierClient.create(courier)
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Failed creation courier with empty password")
    public void failedCreateCourierWithEmptyPassword() {
        Courier courier = generator.genericCourierWithEmptyPassword();
        courierClient.create(courier)
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Failed creation courier with existing courier")
    public void failedCreateWithExistingCourier() {
        Courier courier = generator.generic();
        courierClient.create(courier);

        courierClient.create(courier)
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

}
