package be.algielen;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsersDaoImpl implements UsersDao {
	private static final Class<User> persistentClass = User.class;
	private final EntityManager entityManager = SessionManager.INSTANCE.createEntityManager();

	public UsersDaoImpl() {
	}

	public User getUser(long id) {
		return entityManager.find(persistentClass, id);
	}

	public User createUser(String name) {
		User user = new User(name);
		entityManager.persist(user);
		return user;
	}

	public boolean exists(String name) {
		TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.name LIKE ?1", persistentClass);
		query.setParameter(1, name);
		List<User> list = query.getResultList();
		return list.size() == 0;
	}

	public List<User> findAll() {
		return entityManager.createQuery("FROM User", persistentClass).getResultList();
	}
}
