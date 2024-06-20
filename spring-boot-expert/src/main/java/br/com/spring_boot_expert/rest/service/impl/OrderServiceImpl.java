package br.com.spring_boot_expert.rest.service.impl;

import br.com.spring_boot_expert.domain.Client;
import br.com.spring_boot_expert.domain.Order;
import br.com.spring_boot_expert.domain.OrderItem;
import br.com.spring_boot_expert.domain.Product;
import br.com.spring_boot_expert.exceptions.BusinessRulesException;
import br.com.spring_boot_expert.repositories.ClientRepository;
import br.com.spring_boot_expert.repositories.OrderItemRepository;
import br.com.spring_boot_expert.repositories.OrderRepository;
import br.com.spring_boot_expert.repositories.ProductRepository;
import br.com.spring_boot_expert.rest.dto.OrderDTO;
import br.com.spring_boot_expert.rest.dto.OrderItemDTO;
import br.com.spring_boot_expert.rest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Integer clientId = orderDTO.getClient();
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new BusinessRulesException("Invalid Client ID."));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setClient(client);

        List<OrderItem> orderItems = convertItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);

        return order;
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRulesException("It's not possible to place an order without items");
        }

        return items.stream()
                .map(dto -> {
                    Integer productId = dto.getProduct();
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new BusinessRulesException("Invalid Product ID: " + productId));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setAmount(dto.getAmount());
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
