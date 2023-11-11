package org.myproject.cash.cockpit.api.service.vault;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.VaultRepository;
import org.myproject.cash.cockpit.api.repository.model.VaultDAO;
import org.myproject.cash.cockpit.api.rest.model.VaultDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VaultRepositoryService {

    private final VaultRepository vaultRepository;
    private final ToDTOMapper toDTOMapper;

    public double getTotalSum() {
        return vaultRepository.findAll().stream().map(VaultDAO::getSum).reduce(0d, Double::sum);
    }

    public List<VaultDTO> findAll() {
        return vaultRepository.findAll().stream()
                .map(toDTOMapper::toVaultDTO)
                .toList();
    }

    public void saveVault(final double sum) {
        vaultRepository.save(VaultDAO.builder()
                .sum(sum)
                .build());
    }

    public void deleteVault(final UUID id) {
        vaultRepository.deleteById(id);
    }
}
