package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierAssertions {
    @Step("Check successful creation courier")
    public void successfulCreation(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));

    }

    @Step("Check failed creation courier")
    public String failedCreation(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Check successful login courier")
    public int successfulLogin(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }

    @Step("Check failed login courier with empty login or password")
    public void failedCourierLoginWithEmptyLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Check failed login courier with incorrect login or password")
    public void failedCourierLoginWithIncorrectLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Check successful deletion  courier")
    public void successfulDelete(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true));
    }

    @Step("Check failed deletion  courier with empty id")
    public void failedDeleteWithEmptyId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для удаления курьера"));
    }

    @Step("Check failed deletion  courier with nonexistent id")
    public void failedDeleteWithNonexistentId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Курьера с таким id нет."));

    }

}
