package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Organization;
import fr.ensicaen.service.IGenericService;

@Transactional
public class OrganizationService implements IGenericService<Organization> {

    private static final long serialVersionUID = 4572538062281636600L;
    private IGenericDAO<Organization> dao;

	@Override
	public void add(Organization user) {
		dao.create(user);

	}

	@Override
	public void update(Organization user) {
		dao.update(user);
	}

	@Override
	public void delete(Organization user) {
		dao.delete(user);
	}

	@Override
	public Organization find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Organization> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Organization> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Organization> dao) {
		this.dao = dao;
	}

}
