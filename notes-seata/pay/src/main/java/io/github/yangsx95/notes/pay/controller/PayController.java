package io.github.yangsx95.notes.pay.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.yangsx95.notes.pay.entity.Account;
import io.github.yangsx95.notes.pay.mapper.AccountMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping(value = "/pay")
    public void payMoney(
            @RequestParam("accountNo") String accountNo,
            @RequestParam("amount") BigDecimal amount
    ) {

        // 查询账户信息
        List<Account> accounts = accountMapper.selectList(Wrappers.lambdaQuery(Account.class).eq(Account::getAccountNo, accountNo));
        // 如果没查到，插入一条商品信息（这是为了方便测试用的）
        if (accounts.isEmpty()) {
            accountMapper.insert(new Account(null, accountNo, new BigDecimal("100.21")));
            accounts = accountMapper.selectList(Wrappers.lambdaQuery(Account.class).eq(Account::getAccountNo, accountNo));
        }
        Account account = accounts.get(0);

        // 账户余额不足
        if (account.getAccountBalance().compareTo(amount) < 0) {
            throw new RuntimeException("账户余额不足，无法支付");
        }

        // 支付扣减余额
        account.setAccountBalance(account.getAccountBalance().subtract(amount));
        accountMapper.updateById(account);

    }

}
