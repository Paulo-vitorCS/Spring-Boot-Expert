package br.com.spring_boot_expert.rest.controller;

import br.com.spring_boot_expert.domain.Order;
import br.com.spring_boot_expert.domain.OrderItem;
import br.com.spring_boot_expert.domain.enums.OrderStatus;
import br.com.spring_boot_expert.rest.dto.OrderDTO;
import br.com.spring_boot_expert.rest.dto.OrderInformationDTO;
import br.com.spring_boot_expert.rest.dto.OrderItemInformationDTO;
import br.com.spring_boot_expert.rest.dto.UpdateOrderStatusDTO;
import br.com.spring_boot_expert.rest.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return order.getId();
    }

    @GetMapping("{id}")
    public OrderInformationDTO getById(@PathVariable Integer id) {
        return orderService.getCompleteOrder(id)
                .map(order -> convert(order))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    private OrderInformationDTO convert(Order order) {
        return OrderInformationDTO
                .builder()
                .id(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(convert(order.getItems()))
                .build();
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody UpdateOrderStatusDTO status) {
        String newStatus = status.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));
    }

    private List<OrderItemInformationDTO> convert(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(
                item -> OrderItemInformationDTO.builder()
                        .productDescription(item.getProduct().getDescription())
                        .unitPrice(item.getProduct().getUnitPrice())
                        .amount(item.getAmount())
                        .build()
        ).collect(Collectors.toList());
    }

}
