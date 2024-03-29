package app.com.minhasfinancas.service;

import java.util.Optional;

import app.com.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar (String email, String senha);
	Usuario  salvarUsuario (Usuario u);
	void validarEmail (String email);
	Optional<Usuario> obterPorId(Long id);

}
