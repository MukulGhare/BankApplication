package com.MPro.BankApp.app.Security.Service;

import com.MPro.BankApp.app.Customer.Entity.Customer;
import com.MPro.BankApp.app.Customer.Repo.CustomerRepo;
import com.MPro.BankApp.app.Customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo;
        try {
            identityNo = Long.valueOf(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Username must be numeric identityNo");
        }

        Customer c = repo.findByIdentityNo(identityNo);
        if (c == null) {
            throw new UsernameNotFoundException("User not found with identityNo: " + username);
        }
        return new MyUserDetails(c);
    }
}
