package app.com.minhasfinancas.model.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.com.minhasfinancas.model.entity.Lancamento;
import app.com.minhasfinancas.model.enums.TipoLancamento;


public interface LancamentoRepository  extends JpaRepository<Lancamento, Long>  {

	@Query(value = "select sum(l.valor) from Lancamento l join l.usuario u where u.id = :id_usuario and l.tipo = :tipo group by u ")
	BigDecimal obterSaldoPorTipoeUsuario (@Param ("id_usuario") Long id_usuario, @Param("tipo") TipoLancamento tipo);
	
	}