package br.com.ferry.financeapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ferry.financeapi.config.security.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Boolean ativo;

    @JsonManagedReference
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lancamento> lancamentos;

    @JsonManagedReference
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @Embedded
    private Endereco endereco;
}
