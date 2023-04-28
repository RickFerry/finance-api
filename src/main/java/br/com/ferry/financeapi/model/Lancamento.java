package br.com.ferry.financeapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate dataVencimento = LocalDate.now(); 
    private LocalDate dataPagamento = LocalDate.now();
    private BigDecimal valor;
    private String observacao;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;
}
