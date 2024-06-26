package br.com.spring_boot_expert.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInformationDTO {

    private Integer id;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String status;
    private String orderDate;
    private List<OrderItemInformationDTO> items;

}
