package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.geekbrains.domodel.entities.Account;

/**
 * DTO представление сущности Лицевой счет
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

    private Long id;

    private String address;

    private String houseNumber;

    private Double acresAmount;

    public Account toAccount(){
        Account account = new Account();
        account.setId(id);
        account.setAddress(address);
        account.setHouseNumber(houseNumber);
        account.setAcresAmount(acresAmount);
        return account;
    }

    public static AccountDto fromAccount(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAddress(account.getAddress());
        accountDto.setHouseNumber(account.getHouseNumber());
        accountDto.setAcresAmount(accountDto.getAcresAmount());
        return accountDto;
    }
}
