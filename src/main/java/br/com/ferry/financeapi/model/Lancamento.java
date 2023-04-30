package br.com.ferry.financeapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.ferry.financeapi.model.enums.TipoLancamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 2405172221950251807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    public void inativar() {
        this.ativo = false;
    }
}
