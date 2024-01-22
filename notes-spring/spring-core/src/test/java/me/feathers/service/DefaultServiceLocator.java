package me.feathers.service;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/31
 */
public class DefaultServiceLocator {

    private static AccountService accountService = new AccountService();
    private static TestService testService = new TestService();
    
    public AccountService createAccountService() {
        return accountService;
    }
    
    public TestService createTestService() {
        return testService;
    }
    
    
}
