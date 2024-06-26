package br.com.spring_boot_expert.rest.service;

import br.com.spring_boot_expert.domain.Order;
import br.com.spring_boot_expert.domain.enums.OrderStatus;
import br.com.spring_boot_expert.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO orderDTO);

    Optional<Order> getCompleteOrder(Integer id);

    void updateStatus(Integer id, OrderStatus orderStatus);

}
