package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IAccountDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.service.IAccountService;

@Transactional
public class AccountService implements IAccountService {

	private IAccountDAO accountDAO;

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

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

}
