package br.ueg.openodonto.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.simple.jdbc.EntityManager;
import br.com.simple.jdbc.dao.DaoFactory;
import br.com.simple.jdbc.orm.OrmFormat;
import br.com.simple.jdbc.sql.CrudQuery;
import br.com.simple.jdbc.sql.IQuery;
import br.ueg.openodonto.controle.exception.LoginInvalidoException;
import br.ueg.openodonto.dominio.Usuario;
import br.ueg.openodonto.persistencia.LoginManager;

/**
 * @author Vinicius
 * 
 */
public class DaoLogin implements LoginManager, Serializable {

	private static final long serialVersionUID = 2771520846029661291L;

	private EntityManager<Usuario> daoUsuario;

	public DaoLogin() {
		this.daoUsuario = DaoFactory.getInstance().getDao(Usuario.class);
	}

	public Usuario autenticar(String login, String senha) {
		Usuario usuario = new Usuario(login,senha);
		return autenticar(usuario);
	}
	
	public Usuario autenticar(Usuario usuario) {
		OrmFormat orm = new OrmFormat(usuario);		
		IQuery query = CrudQuery.getSelectQuery(Usuario.class, orm.format("user", "senha"),  "*");
		try {
			List<Usuario> usuarios = daoUsuario.getSqlExecutor().executarQuery(query);
			if (usuarios.size() == 1) {
				return usuarios.get(0);
			} else {
				throw new LoginInvalidoException("Login invalido");
			}
		} catch (Exception e) {
			throw new LoginInvalidoException("Erro de autenticação");
		}
	}

}
