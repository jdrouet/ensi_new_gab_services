package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.service.IGenericService;

@Transactional
public class AccountService implements IGenericService<Account> {
    private static final long serialVersionUID = 4256391571347769886L;

    private IGenericDAO<Account> dao;

	@Override
	public void add(Account user) {
		dao.create(user);

	}

	@Override
	public void update(Account user) {
		dao.update(user);
	}

	@Override
	public void delete(Account user) {
		dao.delete(user);
	}

	@Override
	public Account find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Account> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Account> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Account> dao) {
		this.dao = dao;
	}

}
