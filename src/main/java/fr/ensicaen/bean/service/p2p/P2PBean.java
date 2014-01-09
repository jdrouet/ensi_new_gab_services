package fr.ensicaen.bean.service.p2p;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import fr.ensicaen.bean.AbstractBean;
import fr.ensicaen.bean.FingerBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IGenericService;

/**
 * User: Jérémie Drouet Date: 09/01/14
 */
@ManagedBean
@SessionScoped
public class P2PBean extends AbstractBean {
    private static final long serialVersionUID = 6055540180053992038L;
    private static final String PAGE = "/pages/services/p2p/index.xhtml";

    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    @ManagedProperty("#{fingerBean}")
    private FingerBean fingerBean;

    private Account senderAccount;
    private Account recipientAccount;
    private float amount;

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public IGenericService<Client> getClientService() {
        return clientService;
    }

    public void setClientService(IGenericService<Client> clientService) {
        this.clientService = clientService;
    }

    public IGenericService<Account> getAccountService() {
        return accountService;
    }

    public void setAccountService(IGenericService<Account> accountService) {
        this.accountService = accountService;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Account getRandomAccount() {
        List<Account> lst = this.accountService.getAll();
        lst.removeAll(this.getHomeBean().getClient().getAccountList());
        Collections.shuffle(lst);
        return lst.get(0);
    }

    public void executeTransfer() {
        this.recipientAccount = this.getRandomAccount(); // A modifier si NFC
        Operation op = new Operation(this.senderAccount, this.recipientAccount, this.amount);
        if (this.senderAccount.getDebitList() == null) {
            this.senderAccount.setDebitList(Arrays.asList(op));
        } else {
            this.senderAccount.getDebitList().add(op);
        }
        this.clientService.update(this.senderAccount.getClient());
    }

    public String execute() {
        this.fingerBean.initialize(new FingerBean.Command() {
            private static final long serialVersionUID = 9047155031690329826L;

            @Override
            public void onSuccess() {
                executeTransfer();
            }

            @Override
            public void onFail() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, HomeBean.PAGE);
        return FingerBean.PAGE;
    }

}
