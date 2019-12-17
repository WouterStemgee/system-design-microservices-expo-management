package be.ugent.sysdes2.parking.adapters.external;

import org.springframework.stereotype.Component;

@Component
public class BankAdapter {
    public BankAdapter() {}

    public boolean transactionVerified(double ticketPrice) {
        return true;
    }
}
