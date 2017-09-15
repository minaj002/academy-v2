package com.weststein.handler.currency;

import com.weststein.repository.Currency;
import com.weststein.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by artis on 15/09/2017.
 */

@Component
public class GetAvailableCurrenciesHandler {

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> handle() {
        return currencyRepository.findByConvertOnline(Boolean.TRUE);
    }

}
