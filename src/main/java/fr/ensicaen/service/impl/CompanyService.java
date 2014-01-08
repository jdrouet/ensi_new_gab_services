package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Company;
import fr.ensicaen.service.IGenericService;
import java.util.ArrayList;

@Transactional
public class CompanyService implements IGenericService<Company> {

	private IGenericDAO<Company> dao;

	@Override
	public void add(Company user) {
		dao.create(user);

	}

	@Override
	public void update(Company user) {
		dao.update(user);
	}

	@Override
	public void delete(Company user) {
		dao.delete(user);
	}

	@Override
	public Company find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Company> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Company> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Company> dao) {
		this.dao = dao;
	}
        
        public Company getCompanyByName(String name) {
            List<Company> companies = getAll();
            for (Company company : companies) {
                if (company.getName() != null)
                    if(company.getName().equals(name))
                        return company;
            }
            return null;
        }

}
