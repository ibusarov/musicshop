package myproject.model.service.impl;

import myproject.model.service.RoleService;
import myproject.repository.RoleRepository;
import myproject.service.RoleServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public RoleServiceModel findOneByRole(String role) {
        return this.roleRepository
                .findByRole(role)
                .map(rol -> this.modelMapper.map(rol,RoleServiceModel.class))
                .orElse(null);
    }
}
