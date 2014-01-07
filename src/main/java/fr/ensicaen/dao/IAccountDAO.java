package fr.ensicaen.dao;

import java.util.List;

import fr.ensicaen.entity.Account;

public interface IAccountDAO {

	public void create(Account obj);

	public Account find(Long id);

	public List<Account> readAll();

	public void update(Account obj);

	public void delete(Account obj);
}
