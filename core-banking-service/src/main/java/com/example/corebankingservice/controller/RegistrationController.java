/* Focuses on user registration and login functionalities. 
This includes handling registration forms and processing user input.*/

package com.example.corebankingservice.controller;

@controller
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@ModelAttribute CustomerDto customerDto) {
        userService.registerUser(customerDto);
        return ResponseEntity.status(HttpsStatus.CREATED).body("User registered successfully.");
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("customerDto",new CustomerDto());
        return "register"; // Thymeleaf template name
    }
    
}
