package br.com.spring_boot_expert.rest.controller;

import br.com.spring_boot_expert.domain.Order;
import br.com.spring_boot_expert.rest.dto.OrderDTO;
import br.com.spring_boot_expert.rest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return order.getId();
    }
}
