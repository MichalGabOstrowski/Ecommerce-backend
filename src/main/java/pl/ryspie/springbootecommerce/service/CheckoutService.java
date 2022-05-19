package pl.ryspie.springbootecommerce.service;

import pl.ryspie.springbootecommerce.dto.Purchase;
import pl.ryspie.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
