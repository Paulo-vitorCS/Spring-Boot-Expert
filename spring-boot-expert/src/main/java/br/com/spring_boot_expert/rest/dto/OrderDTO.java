package br.com.spring_boot_expert.rest.dto;

import br.com.spring_boot_expert.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "{client-id.field.required}")
    private Integer client;

    @NotNull(message = "{total.field.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{order-items.field.required}")
    private List<OrderItemDTO> items;

}
