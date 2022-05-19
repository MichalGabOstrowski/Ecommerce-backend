package pl.ryspie.springbootecommerce.dto;

import lombok.Data;
import pl.ryspie.springbootecommerce.entity.Address;
import pl.ryspie.springbootecommerce.entity.Customer;
import pl.ryspie.springbootecommerce.entity.Order;
import pl.ryspie.springbootecommerce.entity.OrderItem;

import javax.persistence.SecondaryTable;
import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;

}
