package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.AccountType;
import fr.ensicaen.service.IGenericService;

@Transactional
public class AccountTypeService implements IGenericService<AccountType> {

	private IGenericDAO<AccountType> dao;

	@Override
	public void add(AccountType user) {
		dao.create(user);

	}

	@Override
	public void update(AccountType user) {
		dao.update(user);
	}

	@Override
	public void delete(AccountType user) {
		dao.delete(user);
	}

	@Override
	public AccountType find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<AccountType> findAll() {
		return dao.readAll();
	}

	public IGenericDAO<AccountType> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<AccountType> dao) {
		this.dao = dao;
	}

}
