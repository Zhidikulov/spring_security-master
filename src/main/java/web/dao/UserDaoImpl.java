package web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private final Map<String, User> userMap = Collections.singletonMap("test",
            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map


    @Autowired
    private SessionFactory ses;




    @Override
    public User getUserByName(String name) {
        if (!userMap.containsKey(name)) {
            return null;
        }

        return userMap.get(name);
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<User> allUsers() {
        Session session = ses.getCurrentSession();
       return session.createQuery("From User").list();
    }

    @Override
    public void add(User user) {
        Session session = ses.getCurrentSession();
        session.save(user);
    }

    @Override
    public void delete(User user) {
        Session session = ses.getCurrentSession();
        session.delete(user);

    }

    @Override
    public void edit(User user) {
        Session session = ses.getCurrentSession();
        session.update(user);


    }

    @Override
    public User getById(long id) {
        Session session = ses.getCurrentSession();
        return session.get(User.class, id);
    }
}

