package fr.ensicaen.bean.service.p2p;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.ensicaen.bean.AbstractBean;
import fr.ensicaen.bean.FingerBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IGenericService;

/**
 * User: Jérémie Drouet Date: 09/01/14
 */
@ManagedBean
@SessionScoped
public class P2PBean extends AbstractBean {
    private static final long serialVersionUID = 6055540180053992038L;

    @ManagedProperty("#{accountService}")
    private IGenericService<Account> accountService;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    @ManagedProperty("#{fingerBean}")
    private FingerBean fingerBean;

    private Account senderAccount;
    private Account recipientAccount;
    private float amount;

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public String getRecipientText() {
        if (this.recipientAccount == null) {
            return "";
        } else {
            return String.format("%s - %s - %d", this.recipientAccount
                    .getClient().getName(), this.recipientAccount
                    .getAccountType().getName(), this.recipientAccount
                    .getIdAccount());
        }
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public IGenericService<Account> getAccountService() {
        return accountService;
    }

    public void setAccountService(IGenericService<Account> accountService) {
        this.accountService = accountService;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Fausse lecture de tag, on prend un compte aléatoire dans la base de
     * données et on fait une transaction
     */
    public void readTag() {
        List<Account> lst = this.accountService.getAll();
        if (this.senderAccount == null) {
            this.recipientAccount = null;
        } else {
            lst.removeAll(this.getHomeBean().getClient().getAccountList());
            if (lst.size() > 0) {
                Collections.shuffle(lst);
                this.recipientAccount = lst.get(0);
            } else {
                this.recipientAccount = null;
            }
        }
    }

    public void execute() {
        this.fingerBean.openDialog(new FingerBean.Command() {
            private static final long serialVersionUID = 8735276544234605809L;

            @Override
            public void onSuccess() {
                generateTransfer();
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void generateTransfer() {
        Operation op = new Operation(this.senderAccount, this.recipientAccount, this.amount);
        if (this.senderAccount.getDebitList() != null) {
            this.senderAccount.getDebitList().add(op);
        } else {
            this.senderAccount.setDebitList(Arrays.asList(op));
        }
        if (this.recipientAccount.getCreditList() != null) {
            this.recipientAccount.getCreditList().add(op);
        } else {
            this.recipientAccount.setCreditList(Arrays.asList(op));
        }
        this.accountService.update(this.senderAccount);
        this.accountService.update(this.recipientAccount);
    }

}
