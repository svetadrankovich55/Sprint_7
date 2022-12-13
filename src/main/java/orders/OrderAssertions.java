package orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.*;

public class OrderAssertions {

    @Step("Check successful creation order")
    public int successfulCreation(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0))
                .extract()
                .path("track");
    }

    @Step("Check successful getting orders")
    public void successfulGetOrders(Orders orders) {
        MatcherAssert.assertThat(orders, notNullValue());
    }

    @Step("Check successful getting order by track")
    public int successfulGetOrderByTrack(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("order.id", greaterThan(0))
                .extract()
                .path("order.id");
    }

    @Step("Check successful getting order by empty track")
    public void failedGetOrderByTrackWithEmptyTrack(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Step("Check successful getting order by empty track")
    public void failedDeleteCourierWithNonexistentId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Заказ не найден"));
    }

    @Step("Check successful acceptance order")
    public void successfulAcceptOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true));
    }

    @Step("Check acceptance order with empty order id or courierId")
    public void failedAcceptOrderWithEmptyOrderIdOrCourierId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Step("Check acceptance order with nonexistent order id")
    public void failedAcceptOrderWithNonexistentOrderId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Заказа с таким id не существует"));
    }

    @Step("Check acceptance order with nonexistent courierId")
    public void failedAcceptOrderWithNonexistentCourierId(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Курьера с таким id не существует"));
    }

}
