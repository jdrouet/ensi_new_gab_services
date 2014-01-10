package fr.ensicaen.bean;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Action;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Company;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.ICompanyService;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.IServiceService;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class StoreBean extends AbstractBean {
    private static final long serialVersionUID = 1980070445064631124L;

    @ManagedProperty("#{serviceService}")
    private IServiceService serviceService;

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

    @ManagedProperty("#{actionService}")
    private IGenericService<Action> actionService;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    @ManagedProperty("#{companyService}")
    private ICompanyService companyService;

    @ManagedProperty("#{fingerBean}")
    private FingerBean fingerBean;

    private Tag selectedTag;

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public FingerBean getFingerBean() {
        return fingerBean;
    }

    public void setFingerBean(FingerBean fingerBean) {
        this.fingerBean = fingerBean;
    }

    public Client getClient() {
        return this.homeBean.getClient();
    }

    public IGenericService<Client> getClientService() {
        return clientService;
    }

    public void setClientService(IGenericService<Client> clientService) {
        this.clientService = clientService;
    }

    public IGenericService<Action> getActionService() {
        return actionService;
    }

    public void setActionService(IGenericService<Action> actionService) {
        this.actionService = actionService;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public IServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public IGenericService<Account> getAccountService() {
        return accountService;
    }

    public void setAccountService(IGenericService<Account> accountService) {
        this.accountService = accountService;
    }

    public ICompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(ICompanyService companyService) {
        this.companyService = companyService;
    }

    public List<Service> getServiceList() {
        return this.serviceService.getServicesByTag(this.getClient(), this.selectedTag);
    }

    public void addService(Service service) {
            this.getClient().getServiceList().add(service);
            this.clientService.update(this.getClient());
    }
    
    public void buyService(Service service) {
        Company bank = this.companyService.getCompanyByName("Banque");
        Account bankAccount = bank.getAccountList().get(0);
        Account clientAccount = this.homeBean.getAccount();
        float amount = service.getCost();
        Operation op = new Operation(clientAccount, bankAccount, amount);
        clientAccount.debit(op);
        bankAccount.credit(op);
        this.getClient().getServiceList().add(service);
        this.accountService.update(clientAccount);
    }

}
