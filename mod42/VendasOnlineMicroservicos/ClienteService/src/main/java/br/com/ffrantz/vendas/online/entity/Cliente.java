package br.com.ffrantz.vendas.online.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="Cliente", description = "Cliente")
public class Cliente {

    @Id
    @Schema(description = "Identificador Único")
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false)
    private String nome;

    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "Cpf", nullable = false)
    private Long cpf;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    @Schema(description = "E-mail", minLength = 1, maxLength = 50, nullable = false)
    @Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
    private String email;

    @NotNull
    @Schema(description = "Telefone", nullable = false)
    private Long telefone;

    @NotNull
    @Size(min = 1, max = 100)
    @Schema(description = "Endereço", minLength = 1, maxLength = 100, nullable = false)
    private String endereco;

    @NotNull
    @Schema(description = "Cidade", nullable = false)
    private String cidade;

    @NotNull
    @Schema(description = "Estado", nullable = false)
    private String estado;
}
