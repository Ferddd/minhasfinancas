package app.com.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import app.com.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
public class UsuarioRepositoryTeste {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void verificarEmail() {
		Usuario user = Usuario.builder().nome("Usuario").email("Usuario@email.com").build();
		repository.save(user);
		
		boolean result = repository.existsByEmail("Usuario@email.com");
		
		Assertions.assertThat(result).isTrue();
	}
	
}
