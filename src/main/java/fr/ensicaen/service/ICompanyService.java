package fr.ensicaen.service;

import fr.ensicaen.entity.Company;

/**
 * User: Jérémie Drouet
 * Date: 08/01/14
 */
public interface ICompanyService extends IGenericService<Company> {

    public Company getCompanyByName(String name);

}
