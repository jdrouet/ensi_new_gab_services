package fr.ensicaen.bean.service.consult;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IGenericService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class ConsultationBean implements Serializable {
    private static final long serialVersionUID = 1725763280813270527L;

    @ManagedProperty("#{operationService}")
    private IGenericService<Operation> operationService;

    @ManagedProperty("#{homeBean.client}")
    private Client client;

    public IGenericService<Operation> getOperationService() {
        return operationService;
    }

    public void setOperationService(IGenericService<Operation> operationService) {
        this.operationService = operationService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Account> getAccountList() {
        return this.getClient().getAccountList();
    }

    public List<Operation> getOperationList(Account account) {
        List<Operation> operations = new ArrayList<>();
        operations.addAll(account.getCreditList());
        operations.addAll(account.getDebitList());
        Collections.sort(operations, new Comparator<Operation>() {
            @Override
            public int compare(Operation operation, Operation operation2) {
                return operation.getEvent().compareTo(operation2.getEvent());
            }
        });
        return operations;
    }
}
