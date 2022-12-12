import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.Order;
import orders.OrderAssertions;
import orders.OrdersClient;
import orders.OrdersGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final OrdersGenerator generator = new OrdersGenerator();
    private  final OrdersClient ordersClient = new OrdersClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();

   @Parameterized.Parameter()
    public List <String> color;

    @Parameterized.Parameters(name = "Color: {0}")
    public static Object[][] params() {
        return new Object[][] {
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("")}
        };
    }

    @Test
    @DisplayName("Successful creation order with parameters")
    public void successfulCreateOrder() {
        Order order = generator.generic();
        ValidatableResponse creationResponse = ordersClient.create(order);
        orderAssertions.successfulCreation(creationResponse);
    }

}
