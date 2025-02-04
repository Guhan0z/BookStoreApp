package com.bookstore.order_service.util;

import com.bookstore.order_service.domain.model.Address;
import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.domain.model.Customer;
import com.bookstore.order_service.domain.model.OrderItemDTO;
import org.instancio.Instancio;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.instancio.Select.field;

public class TestDataUtil {

    private static final List<String> VAILD_COUNTRIES = Arrays.asList("India", "Germany");
    private static final Set<OrderItemDTO> VALID_ITEMS = Set.of(
            new OrderItemDTO("P104", "Book", new BigDecimal("14.50"), 2)
    );
    private static final Set<OrderItemDTO> INVALID_ITEMS = Set.of(
            new OrderItemDTO("invalid_code", "SOME BOOK", new BigDecimal("1.50"), 10)
    );

    public static CreateOrderRequest createValidOrderRequest() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), generators -> generators.text().pattern("#a#a#a#a#a@mail.com"))
                .set(field(CreateOrderRequest::orderItems), VALID_ITEMS)
                .generate(field(Address::country), generators -> generators.oneOf(VAILD_COUNTRIES))
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithInvalidCustomer() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), generators -> generators.text().pattern("#a#a#a#a#a@mail.com"))
                .set(field(Customer::name), "")
                .set(field(CreateOrderRequest::orderItems), VALID_ITEMS)
                .generate(field(Address::country), generators -> generators.oneOf(VAILD_COUNTRIES))
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithInvalidAddress() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), generators -> generators.text().pattern("#a#a#a#a#a@mail.com"))
                .set(field(Address::pinCode), "")
                .set(field(CreateOrderRequest::orderItems), VALID_ITEMS)
                .generate(field(Address::country), generators -> generators.oneOf(VAILD_COUNTRIES))
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithNoItems() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), generators -> generators.text().pattern("#a#a#a#a#a@mail.com"))
                .set(field(CreateOrderRequest::orderItems), Set.of())
                .generate(field(Address::country), generators -> generators.oneOf(VAILD_COUNTRIES))
                .create();
    }
}
