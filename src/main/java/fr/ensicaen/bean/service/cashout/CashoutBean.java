/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ensicaen.bean.service.cashout;

import fr.ensicaen.bean.FingerBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Company;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.ICompanyService;
import fr.ensicaen.service.IGenericService;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * @author Alexandre
 */

@ManagedBean
@SessionScoped
public class CashoutBean implements Serializable {
    private static final long serialVersionUID = 4944558785307729996L;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    @ManagedProperty("#{companyService}")
    private ICompanyService companyService;

    @ManagedProperty("#{fingerBean}")
    private FingerBean fingerBean;

    List<Integer> amounts;

    public CashoutBean() {
        this.amounts = new ArrayList<>(Arrays.asList(10, 20, 40, 60, 80, 100));
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }

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

    public void retirer(int amount) {
        Company bank = this.companyService.getCompanyByName("Retrait");
        Account bankAccount = bank.getAccountList().get(0);
        Account clientAccount = this.homeBean.getAccount();
        Operation op = new Operation(clientAccount, bankAccount, amount);
        clientAccount.debit(op);
        bankAccount.credit(op);
        this.accountService.update(clientAccount);
    }

    public String execute(final int amount) {
        this.fingerBean.initialize(new FingerBean.Command() {
            private static final long serialVersionUID = 254803664828902353L;

            @Override
            public void onSuccess() {
                retirer(amount);
            }

            @Override
            public void onFail() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, "/pages/services/cashout/out.xhtml");
        return FingerBean.PAGE;
    }

}
