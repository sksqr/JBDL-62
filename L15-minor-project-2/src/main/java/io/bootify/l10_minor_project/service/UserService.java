package io.bootify.l10_minor_project.service;

import io.bootify.l10_minor_project.domain.Address;
import io.bootify.l10_minor_project.domain.Flat;
import io.bootify.l10_minor_project.domain.User;
import io.bootify.l10_minor_project.model.AddressDTO;
import io.bootify.l10_minor_project.model.UserDTO;
import io.bootify.l10_minor_project.model.UserStatus;
import io.bootify.l10_minor_project.repos.AddressRepository;
import io.bootify.l10_minor_project.repos.FlatRepository;
import io.bootify.l10_minor_project.repos.UserRepository;
import io.bootify.l10_minor_project.util.NotFoundException;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final FlatRepository flatRepository;
    private final AddressRepository addressRepository;

    public UserService(final UserRepository userRepository, final FlatRepository flatRepository,
            final AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.flatRepository = flatRepository;
        this.addressRepository = addressRepository;
    }

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    @Transactional
    public List<UserDTO> findAllWithPagination(Pageable pageable) {
        final List<User> users = userRepository.findAll(pageable).stream().toList();
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        user.setPassword(passwordEncoder.encode("abc@123"));
        return userRepository.save(user).getId();
    }

    public void markInactive(Long userId){
        User user = userRepository.findById(userId).get();
        if(user==null){
            throw new NotFoundException("User does not exist");
        }
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    public void markActive(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            throw new NotFoundException("User does not exist");
        }
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }


    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        Flat flat = user.getFlat();
        if(flat!=null){
            userDTO.setFlatNumber(flat.getNumber());
        }
        AddressDTO addressDTO = new AddressDTO();
        addressService.mapToDTO(user.getAddress(),addressDTO);
        userDTO.setAddress(addressDTO);
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        Flat flat = flatRepository.findByNumber(userDTO.getFlatNumber());
        if(flat == null){
            flat = new Flat();
            flat.setNumber(userDTO.getFlatNumber());
            flatRepository.save(flat);
        }
        user.setFlat(flat);
        final Address address = new Address();
        addressService.mapToEntity(userDTO.getAddress(),address);
        addressRepository.save(address);
        user.setAddress(address);
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return userRepository.existsByPhoneIgnoreCase(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(username);
        if(userDetails==null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }
}
