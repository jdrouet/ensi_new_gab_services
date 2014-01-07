import fr.ensicaen.entity.*;

import java.util.Arrays;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
public class GenerateDatabase {

    public static void main(String[] argv) {
        /** Types de comptes **/
        //
        AccountType t1 = new AccountType();
        t1.setName("Livret A");
        t1.setCeiling(22000);
        //
        AccountType t2 = new AccountType();
        t2.setName("Livret jeune");
        t2.setCeiling(22000);
        t2.setRate(0.0325);
        //
        /** Services **/
        //
        Service s1 = new Service("SNCF", "Service d'achat de billets SNCF");
        //
        /** Personne 1 **/
        //
        Person p1 = new Person("Hervé", "Tété");
        //
        Account a1 = new Account(p1, t1, 42.0);
        //
        Card card = new Card(a1, "123456789012345", "1234");
        //
        a1.setCardList(Arrays.asList(card));
        //
        p1.setAccountList(Arrays.asList(a1));
        //

    }

}
