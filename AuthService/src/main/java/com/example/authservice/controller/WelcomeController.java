package com.example.authservice.controller;

import com.example.authservice.entity.AuthRequest;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
//@RequestMapping("/")
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class WelcomeController {

    ResponseEntity authenticationResponse ;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")
    public String welcome() {
        return "Welcome Rono";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

   /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logintoMCs(@RequestParam String userName,@RequestParam String password)
    {
        String AUTHENTICATION_URL="http://localhost:9395/authenticate";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("userName", userName);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, request, String.class);
        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK))
        {
            return "Login Successfull and Got Token";
        }
        return "Invalid credential";
    }

    @PostMapping(path = "/addMovie")
    public String getProductList(TransactionRequest transactionRequest) {
        Movie movie = transactionRequest.getMovie();
//        TransactionRequest transactionRequest = null
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Movie> entity = new HttpEntity<Movie>(movie,headers);
        RestTemplate restTemplate = new RestTemplate();
                return restTemplate.exchange("http://localhost:9392/addMovie", HttpMethod.POST, entity, String.class).getBody();
     }*/

}
