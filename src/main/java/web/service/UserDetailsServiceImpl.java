package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    BCryptPasswordEncoder crypt;
//
//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByName(s);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers(){
        return userDao.allUsers();
    }

    @Transactional
    @Override
    public void add(User user){
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(crypt.encode(user.getPassword()));
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(User user){
        userDao.delete(user);
    }

    @Transactional
    @Override
    public void edit(User user){
        userDao.edit(user);
    }

    @Transactional
    @Override
    public User getById(long id){
        return userDao.getById(id);
    }


}
