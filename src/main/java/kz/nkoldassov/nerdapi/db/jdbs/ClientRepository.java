package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.Client;

public interface ClientRepository {

    Client getClient(Long clientId);

    Client getClientByEmail(String email);
}
