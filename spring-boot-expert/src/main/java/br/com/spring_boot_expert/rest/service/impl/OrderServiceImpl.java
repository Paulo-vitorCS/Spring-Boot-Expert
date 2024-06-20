package br.com.spring_boot_expert.rest.service.impl;

import br.com.spring_boot_expert.repositories.OrderRepository;
import br.com.spring_boot_expert.rest.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
