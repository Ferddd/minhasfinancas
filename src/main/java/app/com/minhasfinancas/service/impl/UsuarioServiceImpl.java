package app.com.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.com.minhasfinancas.exception.ErroAutenticacao;
import app.com.minhasfinancas.exception.RegraNegocioException;
import app.com.minhasfinancas.model.entity.Usuario;
import app.com.minhasfinancas.model.repository.UsuarioRepository;
import app.com.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> u = repository.findByEmail(email);
		if(!u.isPresent()) {
			throw new ErroAutenticacao("Email não encontrado"); 
		}
		if (!u.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha invalida"); 
		}
		return u.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario u) {
		validarEmail(u.getEmail());
		
		return repository.save(u);
	}

	@Override
	public void validarEmail(String email) {
		if(repository.existsByEmail(email)) {
			throw new RegraNegocioException("email já cadastrado.");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
