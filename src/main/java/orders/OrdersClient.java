package orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import utils.Client;

public class OrdersClient extends Client {
    protected final String ROOT = "/orders";

    @Step("Send POST request to /api/v1/orders for creation order")
    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Send GET request to /api/v1/orders for getting orders list")
    public Orders getOrders() {
        return spec()
                .get(ROOT)
                .body().as(Orders.class);
    }

    @Step("Send GET request to /api/v1/orders/track for getting order by track")
    public ValidatableResponse getOrderByTrack(int track) {
        return spec()
                .queryParam("t", track)
                .get(ROOT + "/track")
                .then().log().all();
    }

    @Step("Send GET request to /api/v1/orders/track for getting order by empty track")
    public ValidatableResponse getOrderByTrackWithEmptyTrack() {
        return spec()
                .queryParam("t", "")
                .get(ROOT + "/track")
                .then().log().all();
    }

    @Step("Send GET request to /api/v1/orders/track for getting order by nonexistent track")
    public ValidatableResponse getOrderByTrackWithNonexistentTrack() {
        return spec()
                .queryParam("t", "0")
                .get(ROOT + "/track")
                .then().log().all();
    }

    @Step("Send PUT request to /api/v1/orders/accept/:id for acceptance order")
    public ValidatableResponse putAcceptOrder(int orderId, int courierId) {
        return spec()
                .put(ROOT + "/accept/" + orderId + "?" + "courierId=" + courierId)
                .then().log().all();
    }

    @Step("Send PUT request to /api/v1/orders/accept/:id for acceptance order with empty courier Id")
    public ValidatableResponse putAcceptOrderWithEmptyOrderId(int courierId) {
        return spec()
                .put(ROOT + "/accept/" + "courierId=" + courierId)
                .then().log().all();
    }

    @Step("Send PUT request to /api/v1/orders/accept/:id for acceptance order with empty order Id")
    public ValidatableResponse putAcceptOrderWithEmptyCourierId(int orderId) {
        return spec()
                .put(ROOT + "/accept/" + orderId + "?" + "courierId=")
                .then().log().all();
    }

}
