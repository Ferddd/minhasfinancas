package app.com.minhasfinancas.api.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.com.minhasfinancas.api.dto.UsuarioDto;
import app.com.minhasfinancas.exception.ErroAutenticacao;
import app.com.minhasfinancas.exception.RegraNegocioException;
import app.com.minhasfinancas.model.entity.Usuario;
import app.com.minhasfinancas.service.LancamentoService;
import app.com.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private UsuarioService service;
	private LancamentoService lancamento;
	
	public UsuarioController(UsuarioService service, LancamentoService lancamento) {
		this.service = service;
		this.lancamento = lancamento;
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar (@RequestBody UsuarioDto u) { 
		
		try {
			Usuario usuarioAutenticado = service.autenticar(u.getEmail(),u.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity salvar (@RequestBody UsuarioDto u) { 
		
		Usuario usuario = Usuario.builder().nome(u.getNome()).email(u.getEmail()).senha(u.getSenha()).build();
		
		try {
			Usuario usuariosalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuariosalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}/saldo")
	public ResponseEntity obterSaldo (@PathVariable("id") Long id) {
		
		BigDecimal saldo =lancamento.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}
}
