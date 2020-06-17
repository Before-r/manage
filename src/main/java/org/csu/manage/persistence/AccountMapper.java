package org.csu.manage.persistence;

import org.csu.manage.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<Account> getAccountList();
}
