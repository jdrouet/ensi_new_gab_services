package fr.ensicaen.bean.service.sncf;

import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Company;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.impl.CompanyService;
import fr.ensicaen.service.impl.PartnerService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class SncfBean implements Serializable {
    
    private static final long serialVersionUID = -4134614781118798187L;
    
    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;
    
    private String source;
    private String destination;
    private Date start = new Date();
    private List<Voyage> voyages;
    private Voyage selectedVoyage;
    
    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    public IGenericService<Account> getAccountService() {
        return accountService;
    }

    private CompanyService companyService;
    
    public void setAccountService(IGenericService<Account> accountService) {
        this.accountService = accountService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    
    public List<Voyage> getVoyages() {
        return voyages;
    }
    
    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
    
    public Voyage getSelectedVoyage() {
        return this.selectedVoyage;
    }
    
    public void setSelectedVoyage(Voyage voyage) {
        this.selectedVoyage = voyage;
    }
    
    public void rechercherVoyages() {
        int[] minutes = {10,36,55};
        this.voyages = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        DateFormat timeFormat = new SimpleDateFormat("HH");
        int c = 1;
        for(int i=0; i<6; i++) {
            if(i>3)
                c = 2;
            Voyage v = new Voyage(this.source, this.destination, dateFormat.format(this.start), Integer.parseInt(timeFormat.format(this.start))+c+"h"+minutes[i%3], "Intercité", new Float(35.80), i);
            this.voyages.add(v);
        }
    }
    
    public void reserverVoyage(Voyage v) {
        Company sncf = this.companyService.getCompanyByName("sncf");
        Account sncfAccount = sncf.getAccountList().get(0);
        Account clientAccount = this.homeBean.getAccount();
        Float amount = v.getPrix();
        Operation op = new Operation(sncfAccount, clientAccount, amount);
        clientAccount.debit(op, amount);
        sncfAccount.credit(op, amount);
        accountService.update(sncfAccount);
        accountService.update(clientAccount);
    }
}
