import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.*;
import org.junit.Test;


public class GetOrderByTrackTest {

    private final OrdersGenerator generator = new OrdersGenerator();
    private  final OrdersClient ordersClient = new OrdersClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();
    private int track;

    @Test
    @DisplayName("Successful getting order by track")
    public void successfulGetOrderByTrackTest() {
        Order order = generator.generic();
        ValidatableResponse creationResponse = ordersClient.create(order);
        track = orderAssertions.successfulCreation(creationResponse);

        ValidatableResponse response =ordersClient.getOrderByTrack(track);
        orderAssertions.successfulGetOrderByTrack(response);
    }

    @Test
    @DisplayName("Failed getting order by empty track")
    public void failedGetOrderByTrackWithEmptyTrackTest() {
        ValidatableResponse response =ordersClient.getOrderByTrackWithEmptyTrack();
        orderAssertions.failedGetOrderByTrackWithEmptyTrack(response);
    }

    @Test
    @DisplayName("Failed getting order by nonexistent track")
    public void failedGetOrderByTrackWithNonexistentTrackTest() {
        ValidatableResponse response =ordersClient.getOrderByTrackWithNonexistentTrack();
        orderAssertions.failedDeleteCourierWithNonexistentId(response);
    }
}
