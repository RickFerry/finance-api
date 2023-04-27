package br.com.ferry.financeapi.config.security.model;

import org.springframework.security.core.GrantedAuthority;

import br.com.ferry.financeapi.model.enums.Descricao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Permissao implements GrantedAuthority {
	private static final long serialVersionUID = -8951414894540867894L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Descricao descricao;

    @Override
    public String getAuthority() {
        return descricao.toString();
    }
}
