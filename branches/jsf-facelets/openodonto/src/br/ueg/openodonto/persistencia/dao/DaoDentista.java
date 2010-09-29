package br.ueg.openodonto.persistencia.dao;

import br.ueg.openodonto.dominio.Dentista;
import br.ueg.openodonto.persistencia.orm.Dao;

@Dao(classe=Dentista.class)
public class DaoDentista extends DaoAbstractPessoa<Dentista> {

	private static final long serialVersionUID = 7872660758710684668L;

	public DaoDentista() {
		super(Dentista.class);
	}

	@Override
	public Dentista getNewEntity() {
		return new Dentista();
	}

}
