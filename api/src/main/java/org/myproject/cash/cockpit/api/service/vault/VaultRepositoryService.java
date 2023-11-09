package org.myproject.cash.cockpit.api.service.vault;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.VaultRepository;
import org.myproject.cash.cockpit.api.repository.model.VaultDAO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaultRepositoryService {

    private final VaultRepository vaultRepository;

    public double getTotalSum() {
        return vaultRepository.findAll().stream().map(VaultDAO::getSum).reduce(0d, Double::sum);
    }
}
