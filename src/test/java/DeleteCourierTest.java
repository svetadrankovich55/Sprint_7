import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class DeleteCourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();
    private final CourierAssertions courierAssertions = new CourierAssertions();

    private int courierId;

    @Test
    @DisplayName("Successful deletion courier")
    public void successfulDeleteCourier() {
        Courier courier = generator.random();
        ValidatableResponse creationResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationResponse);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierId = courierAssertions.successfulLogin(loginResponse);

        if (courierId > 0) {
            ValidatableResponse response = courierClient.delete(courierId);
            courierAssertions.successfulDelete(response);
        }
    }

    @Test
    @DisplayName("Failed deletion courier with empty courier id")
    public void failedDeleteCourierWithEmptyCourierId() {
            ValidatableResponse response = courierClient.deleteWithEmptyCourierId();
            courierAssertions.failedDeleteWithEmptyId(response);

    }

    @Test
    @DisplayName("Failed deletion courier with non-existent courier id")
    public void failedDeleteCourierWithNonexistentId() {

        ValidatableResponse response = courierClient.deleteWithNonexistentId();
        courierAssertions.failedDeleteWithNonexistentId(response);

    }

}
