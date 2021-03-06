package fr.ensicaen.bean.service.sncf;

import fr.ensicaen.bean.AbstractBean;
import fr.ensicaen.bean.FingerBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Company;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.ICompanyService;
import fr.ensicaen.service.IGenericService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class SncfBean extends AbstractBean {

    private static final long serialVersionUID = -4134614781118798187L;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    @ManagedProperty("#{companyService}")
    private ICompanyService companyService;
    
    @ManagedProperty("#{fingerBean}")
    private FingerBean fingerBean;

    private String source;
    private String destination;
    private Date start = new Date();
    private List<Voyage> voyages;

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

    public FingerBean getFingerBean() {
        return fingerBean;
    }

    public void setFingerBean(FingerBean fingerBean) {
        this.fingerBean = fingerBean;
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
    
    

    public void rechercherVoyages() {
        int[] minutes = {10, 36, 55};
        this.voyages = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        DateFormat timeFormat = new SimpleDateFormat("HH");
        int c = 1;
        for (int i = 0; i < 6; i++) {
            if (i > 3) {
                c = 2;
            }
            Voyage v = new Voyage(this.source, this.destination, dateFormat.format(this.start), Integer.parseInt(timeFormat.format(this.start)) + c + "h" + minutes[i % 3], "Intercité", new Float(35.80), i);
            this.voyages.add(v);
        }
    }

    public void reserverVoyage(Voyage v) {
        Company sncf = this.companyService.getCompanyByName("SNCF");
        if(sncf != null) {
            Account sncfAccount = sncf.getAccountList().get(0);
            if(sncfAccount != null) {
                Account clientAccount = this.homeBean.getAccount();
                float amount = v.getPrix();
                Operation op = new Operation(clientAccount, sncfAccount, amount);
                clientAccount.debit(op);
                sncfAccount.credit(op);
                //this.accountService.update(sncfAccount);
                this.accountService.update(clientAccount);
            }
            else
                System.err.println("SNCF Account unreachable !");
        }
        else
            System.err.println("SNCF company unreachable !");
    }
    
    public String execute(final Voyage v) {
        this.fingerBean.initialize(new FingerBean.Command() {
            private static final long serialVersionUID = 254803664828902353L;

            @Override
            public void onSuccess() {
                reserverVoyage(v);
            }

            @Override
            public void onFail() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, "/pages/services/sncf/print.xhtml");
        return FingerBean.PAGE;
    }
}
