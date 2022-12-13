import io.qameta.allure.junit4.DisplayName;
import orders.OrderAssertions;
import orders.Orders;
import orders.OrdersClient;
import org.junit.Test;



public class GetOrdersTest {

    private final OrdersClient ordersClient = new OrdersClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();

    @Test
    @DisplayName("Successful getting orders list")
    public void getOrdersList() {
        Orders orders = ordersClient.getOrders();
        orderAssertions.successfulGetOrders(orders);
    }

}
