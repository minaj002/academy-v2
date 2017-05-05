package com.weststein.controller.secured.model;

import com.weststein.repository.Account;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Accounts {

    private List<Account> accounts;

}
