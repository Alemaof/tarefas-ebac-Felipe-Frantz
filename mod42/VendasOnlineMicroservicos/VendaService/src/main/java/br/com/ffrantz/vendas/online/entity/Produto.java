package br.com.ffrantz.vendas.online.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private String id;

    @NotNull
    @Size(min = 2, max = 10)
    private String codigo;

    @NotNull
    @Size(min = 1, max = 50)
    private String nome;

    @NotNull
    @Size(min = 1, max = 50)
    private String descricao;

    @NotNull
    private BigDecimal valor;
}
