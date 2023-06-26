package com.jacob;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // Controller for a REST API
@RequestMapping("account") // Paano mamamap yung request, "account" ay yung recipient ng request
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }


    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(String accountId) {
        Account account = accountService.get(accountId);

        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegisterDate(account.getCreationDate());

        return response;
    }
}
