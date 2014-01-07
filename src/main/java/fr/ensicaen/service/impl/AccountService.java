package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.GenericDAO;
import fr.ensicaen.service.IAccountService;
import fr.ensicaen.entity.Account;

@Transactional
public class AccountService implements IAccountService {

	private GenericDAO<Account, Long> accountDAO;

	@Override
	public void addAccount(Account user) {
		accountDAO.create(user);
	}

	@Override
	public void updateAccount(Account user) {
		accountDAO.update(user);
	}

	@Override
	public void deleteAccount(Account user) {
		accountDAO.delete(user);
	}

	@Override
	public Account findAccount(Long id) {
		return accountDAO.find(id);
	}

	@Override
	public List<Account> getAccounts() {
		return accountDAO.readAll();
	}

}
