import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.Order;
import orders.OrderAssertions;
import orders.OrdersClient;
import orders.OrdersGenerator;
import org.junit.Test;

public class AcceptOrderTest {

    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();
    private final CourierAssertions courierAssertions = new CourierAssertions();
    private final OrdersGenerator ordersGenerator = new OrdersGenerator();
    private  final OrdersClient ordersClient = new OrdersClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();
    private int track;
    private int orderId;
    private int courierId;

    @Test
    @DisplayName("Successful acceptance order")
    public void successfulAcceptOrder() {
        Courier courier = courierGenerator.random();
        ValidatableResponse creationCourierResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationCourierResponse);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierId = courierAssertions.successfulLogin(loginResponse);

        Order order = ordersGenerator.generic();
        ValidatableResponse creationOrderResponse = ordersClient.create(order);
        track =  orderAssertions.successfulCreation(creationOrderResponse);

        ValidatableResponse response =ordersClient.getOrderByTrack(track);
        orderId = orderAssertions.successfulGetOrderByTrack(response);

        ValidatableResponse acceptOrderResponse = ordersClient.putAcceptOrder(orderId,courierId);
        orderAssertions.successfulAcceptOrder(acceptOrderResponse);

    }

    @Test
    @DisplayName("Failed acceptance order with empty order id")
    public void failedAcceptOrderWithEmptyOrderId() {
        Courier courier = courierGenerator.random();
        ValidatableResponse creationCourierResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationCourierResponse);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierId = courierAssertions.successfulLogin(loginResponse);

        ValidatableResponse acceptOrderResponse = ordersClient.putAcceptOrderWithEmptyOrderId(courierId);
        orderAssertions.failedAcceptOrderWithEmptyOrderIdOrCourierId(acceptOrderResponse);

    }

    @Test
    @DisplayName("Failed acceptance order with empty courier id")
    public void failedAcceptOrderWithEmptyCourierId() {

        Order order = ordersGenerator.generic();
        ValidatableResponse creationOrderResponse = ordersClient.create(order);
        track =  orderAssertions.successfulCreation(creationOrderResponse);

        ValidatableResponse response =ordersClient.getOrderByTrack(track);
        orderId = orderAssertions.successfulGetOrderByTrack(response);

        ValidatableResponse acceptOrderResponse = ordersClient.putAcceptOrderWithEmptyCourierId(orderId);
        orderAssertions.failedAcceptOrderWithEmptyOrderIdOrCourierId(acceptOrderResponse);

    }

    @Test
    @DisplayName("Failed acceptance order with empty courier id")
    public void failedAcceptOrderWithNonexistentOrderId() {

        Courier courier = courierGenerator.random();
        ValidatableResponse creationCourierResponse = courierClient.create(courier);
        courierAssertions.successfulCreation(creationCourierResponse);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        courierId = courierAssertions.successfulLogin(loginResponse);

        orderId = 0;

        ValidatableResponse acceptOrderResponse = ordersClient.putAcceptOrder(orderId,courierId);
        orderAssertions.failedAcceptOrderWithNonexistentOrderId(acceptOrderResponse);

    }

    @Test
    @DisplayName("Failed acceptance order with nonexistent courier id")
    public void failedAcceptOrderWithNonexistentCourierId() {

        courierId = 0;

        Order order = ordersGenerator.generic();
        ValidatableResponse creationOrderResponse = ordersClient.create(order);
        track =  orderAssertions.successfulCreation(creationOrderResponse);

        ValidatableResponse response =ordersClient.getOrderByTrack(track);
        orderId = orderAssertions.successfulGetOrderByTrack(response);

        ValidatableResponse acceptOrderResponse = ordersClient.putAcceptOrder(orderId,courierId);
        orderAssertions.failedAcceptOrderWithNonexistentCourierId(acceptOrderResponse);

    }

}
