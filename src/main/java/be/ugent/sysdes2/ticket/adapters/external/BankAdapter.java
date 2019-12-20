package be.ugent.sysdes2.ticket.adapters.external;

import org.springframework.stereotype.Component;

@Component
public class BankAdapter {

    public BankAdapter() {}

    public boolean transactionVerified(double price) {
        return true;
    }
}
