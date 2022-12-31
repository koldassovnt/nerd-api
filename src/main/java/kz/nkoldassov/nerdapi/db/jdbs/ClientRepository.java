package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.Client;

public interface ClientRepository {

    Client getClient(Long clientId);

    Client getClientByEmail(String email);

    boolean existsByEmail(String email);

    void saveClientForRegister(Client client);

    Client getClientByVerificationCode(String code);

    void approveClientEmail(Long client);
}
