package app.ccb.services;

import app.ccb.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Boolean clientsAreImported() {

     return this.clientRepository.count() != 0;

    }

    @Override
    public String readClientsJsonFile() {
        // TODO : Implement Me
        return null;
    }

    @Override
    public String importClients(String clients) {
        // TODO : Implement Me
        return null;
    }

    @Override
    public String exportFamilyGuy() {
        // TODO : Implement Me
        return null;
    }
}
