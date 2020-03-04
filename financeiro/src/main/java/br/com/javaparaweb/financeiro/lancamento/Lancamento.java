package br.com.javaparaweb.financeiro.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.javaparaweb.financeiro.categoria.Categoria;
import br.com.javaparaweb.financeiro.cheque.Cheque;
import br.com.javaparaweb.financeiro.conta.Conta;
import br.com.javaparaweb.financeiro.usuario.Usuario;


@Entity
@Table( name = "lancamento")
public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2178284473103040750L;
	
	@Id
	@GeneratedValue
	@Column ( name = "codigo" )
	private Integer lancamento;
	
	//Um usu�rio possui muitas contas
	@ManyToOne( fetch = FetchType.LAZY )
	
	//Ao excluir um usu�rio as contas s�o excluidas 
	@OnDelete( action = OnDeleteAction.CASCADE )
	
	//Chave estrangeira do relacionamento lancamento e usuario
	@JoinColumn( name = "usuario", nullable = false, foreignKey = @ForeignKey( name = "fk_lancamento_usuario" ))
	private Usuario usuario;
	
	//Uma conta possui varios lancamentos
	@ManyToOne( fetch = FetchType.LAZY )
	
	//Ao excluir uma conta exclui tamb�m os lancamentos dessa conta
	@OnDelete( action = OnDeleteAction.CASCADE )
	
	//chave estrangeira do relacionamento lancamento e conta
	@JoinColumn( name = "conta", nullable = false, foreignKey = @ForeignKey( name = "fk_lancamento_conta" ))
	private Conta conta;
	
	//uma categoria esta associada a muitos lancamentos
	@ManyToOne( fetch = FetchType.LAZY )
	
	//ao excluir uma categoria, tamb�m se exclui os lancamentos relacionados
	@OnDelete( action = OnDeleteAction.CASCADE )
	
	//chave estrangeira do relacionamento lancamento e categoria
	@JoinColumn( name="categoria", nullable = false, foreignKey = @ForeignKey( name = "fk_lancamento_categoria" ))
	private Categoria categoria;
	
	//chap 11
	//um lancamento esta relacionado a um chque
	@OneToOne ( fetch = FetchType.LAZY, mappedBy="lancamento")
	private Cheque cheque;
	
	//Definindo a precis� do campo para armazenar somente datas, as outras op��es s�o TIME - somente a hora e
	//TIMESTAMP - hora e data
	@Temporal( TemporalType.DATE )
	private Date data;
	
	private String descricao;
	//precisao de 2 campos no mysql serial decimal(10,2)
	//recomendado pelo hibernate qdo se trata de valores monet�rios
	@Column(precision = 10, scale = 2)
	private BigDecimal valor;

	public Integer getLancamento() {
		return lancamento;
	}

	public void setLancamento(Integer lancamento) {
		this.lancamento = lancamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((cheque == null) ? 0 : cheque.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((lancamento == null) ? 0 : lancamento.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (cheque == null) {
			if (other.cheque != null)
				return false;
		} else if (!cheque.equals(other.cheque))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (lancamento == null) {
			if (other.lancamento != null)
				return false;
		} else if (!lancamento.equals(other.lancamento))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	

}
