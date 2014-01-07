package fr.ensicaen.service;

import java.util.List;

import fr.ensicaen.entity.Account;

public interface IAccountService {

	public void addAccount(Account user);

	public void updateAccount(Account user);

	public void deleteAccount(Account user);

	public Account findAccount(Long id);

	public List<Account> getAccounts();

}
