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

    @NotNull(message = "Please provide the client id")
    private Integer client;

    @NotNull(message = "The total field is required")
    private BigDecimal total;

    @NotEmptyList(message = "The order cannot be placed without items")
    private List<OrderItemDTO> items;

}


//{
//    "client": 1,
//    "total": 100,
//    "items": [
//        {
//        "product": 1,
//        "amount": 10
//        }
//    ]
//}