package com.example.corebankingservice.service;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(CustomerDto customerDto){
        // Validate user input and check for existing users

        Customer newUser = new Customer();
        newUser.setUsername(customerDto.getUsername());
        newUSer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        newUser.setEmail(CustomerDto.getEmail());
        newUser.setFullName(customerDto.getFullName());
        customerRepository.save(newUser);
    }
    
}
