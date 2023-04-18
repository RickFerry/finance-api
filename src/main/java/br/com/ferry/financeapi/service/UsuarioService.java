package br.com.ferry.financeapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.model.Usuario;
import br.com.ferry.financeapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id) throws NotFoundException {
        return usuarioRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario original = usuarioRepository.getReferenceById(id);
        BeanUtils.copyProperties(usuario, original, "id");
        return usuarioRepository.save(original);
    }
}
