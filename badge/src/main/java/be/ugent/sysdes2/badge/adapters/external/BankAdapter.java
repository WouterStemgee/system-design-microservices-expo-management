package be.ugent.sysdes2.badge.adapters.external;

import org.springframework.stereotype.Component;

@Component
public class BankAdapter {

    public BankAdapter() {}

    public boolean transactionVerified(float price) {
        return true;
    }
}
