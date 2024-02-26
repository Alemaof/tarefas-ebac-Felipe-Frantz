package br.com.ffrantz.vendas.online.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Produto", description = "Produto")
public class Produto {

    public enum Status {
        ATIVO, INATIVO;
    }

    @Id
    @Schema(description = "Identificador Único")
    private String id;

    @NotNull
    @Size(min = 2, max = 10)
    @Indexed(unique = true, background = true)
    @Schema(description = "Código", minLength = 1, maxLength = 10, nullable = false)
    private String codigo;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false)
    private String nome;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Descrição", minLength = 1, maxLength = 10, nullable = false)
    private String descricao;

    private BigDecimal valor;

    private Status status;
}
