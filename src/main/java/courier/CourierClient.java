package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import utils.Client;

public class CourierClient extends Client {
    protected final String ROOT = "/courier";


    @Step("Send POST request to /api/v1/courier for creation courier")
    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Send POST request to /api/v1/courier/login for login courier")
    public ValidatableResponse login(CourierCredentials courierCredentials) {
        return spec()
                .body(courierCredentials)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    @Step("Send DELETE request to /api/v1/courier/:id for deletion courier")
    public ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return spec()
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }

    @Step("Send DELETE request to /api/v1/courier/:id for deletion courier with nonexistent Id")
    public ValidatableResponse deleteWithNonexistentId() {
        int courierId = 0;
        String json = String.format("{\"id\": \"%d\"}", courierId);

        return spec()
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }

    @Step("Send DELETE request to /api/v1/courier/:id for deletion courier with empty Id")
    public ValidatableResponse deleteWithEmptyCourierId() {
        return spec()
                .when()
                .delete(ROOT + "/")
                .then().log().all();
    }
}
