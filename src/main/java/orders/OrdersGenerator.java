package orders;

import java.util.List;

public class OrdersGenerator {

    public Order generic() {
        return new Order(
                "Naruto",
                "Uzumaki",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                "5",
                "2022-12-09",
                "I'll become hokage dattebayo!",
                List.of("")
        );
    }

}
