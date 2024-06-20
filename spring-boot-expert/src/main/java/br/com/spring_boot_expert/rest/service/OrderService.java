package br.com.spring_boot_expert.rest.service;

import br.com.spring_boot_expert.domain.Order;
import br.com.spring_boot_expert.rest.dto.OrderDTO;

public interface OrderService {

    Order save(OrderDTO orderDTO);

}
