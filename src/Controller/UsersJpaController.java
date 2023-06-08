/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.BookedAppointments;
import Model.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
public class UsersJpaController implements Serializable {
public static Users selectedUserToUpdate;
    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getBookedAppointmentsCollection() == null) {
            users.setBookedAppointmentsCollection(new ArrayList<BookedAppointments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<BookedAppointments> attachedBookedAppointmentsCollection = new ArrayList<BookedAppointments>();
            for (BookedAppointments bookedAppointmentsCollectionBookedAppointmentsToAttach : users.getBookedAppointmentsCollection()) {
                bookedAppointmentsCollectionBookedAppointmentsToAttach = em.getReference(bookedAppointmentsCollectionBookedAppointmentsToAttach.getClass(), bookedAppointmentsCollectionBookedAppointmentsToAttach.getId());
                attachedBookedAppointmentsCollection.add(bookedAppointmentsCollectionBookedAppointmentsToAttach);
            }
            users.setBookedAppointmentsCollection(attachedBookedAppointmentsCollection);
            em.persist(users);
            for (BookedAppointments bookedAppointmentsCollectionBookedAppointments : users.getBookedAppointmentsCollection()) {
                Users oldUserIdOfBookedAppointmentsCollectionBookedAppointments = bookedAppointmentsCollectionBookedAppointments.getUserId();
                bookedAppointmentsCollectionBookedAppointments.setUserId(users);
                bookedAppointmentsCollectionBookedAppointments = em.merge(bookedAppointmentsCollectionBookedAppointments);
                if (oldUserIdOfBookedAppointmentsCollectionBookedAppointments != null) {
                    oldUserIdOfBookedAppointmentsCollectionBookedAppointments.getBookedAppointmentsCollection().remove(bookedAppointmentsCollectionBookedAppointments);
                    oldUserIdOfBookedAppointmentsCollectionBookedAppointments = em.merge(oldUserIdOfBookedAppointmentsCollectionBookedAppointments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            Collection<BookedAppointments> bookedAppointmentsCollectionOld = persistentUsers.getBookedAppointmentsCollection();
            Collection<BookedAppointments> bookedAppointmentsCollectionNew = users.getBookedAppointmentsCollection();
            Collection<BookedAppointments> attachedBookedAppointmentsCollectionNew = new ArrayList<BookedAppointments>();
            for (BookedAppointments bookedAppointmentsCollectionNewBookedAppointmentsToAttach : bookedAppointmentsCollectionNew) {
                bookedAppointmentsCollectionNewBookedAppointmentsToAttach = em.getReference(bookedAppointmentsCollectionNewBookedAppointmentsToAttach.getClass(), bookedAppointmentsCollectionNewBookedAppointmentsToAttach.getId());
                attachedBookedAppointmentsCollectionNew.add(bookedAppointmentsCollectionNewBookedAppointmentsToAttach);
            }
            bookedAppointmentsCollectionNew = attachedBookedAppointmentsCollectionNew;
            users.setBookedAppointmentsCollection(bookedAppointmentsCollectionNew);
            users = em.merge(users);
            for (BookedAppointments bookedAppointmentsCollectionOldBookedAppointments : bookedAppointmentsCollectionOld) {
                if (!bookedAppointmentsCollectionNew.contains(bookedAppointmentsCollectionOldBookedAppointments)) {
                    bookedAppointmentsCollectionOldBookedAppointments.setUserId(null);
                    bookedAppointmentsCollectionOldBookedAppointments = em.merge(bookedAppointmentsCollectionOldBookedAppointments);
                }
            }
            for (BookedAppointments bookedAppointmentsCollectionNewBookedAppointments : bookedAppointmentsCollectionNew) {
                if (!bookedAppointmentsCollectionOld.contains(bookedAppointmentsCollectionNewBookedAppointments)) {
                    Users oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments = bookedAppointmentsCollectionNewBookedAppointments.getUserId();
                    bookedAppointmentsCollectionNewBookedAppointments.setUserId(users);
                    bookedAppointmentsCollectionNewBookedAppointments = em.merge(bookedAppointmentsCollectionNewBookedAppointments);
                    if (oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments != null && !oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments.equals(users)) {
                        oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments.getBookedAppointmentsCollection().remove(bookedAppointmentsCollectionNewBookedAppointments);
                        oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments = em.merge(oldUserIdOfBookedAppointmentsCollectionNewBookedAppointments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            Collection<BookedAppointments> bookedAppointmentsCollection = users.getBookedAppointmentsCollection();
            for (BookedAppointments bookedAppointmentsCollectionBookedAppointments : bookedAppointmentsCollection) {
                bookedAppointmentsCollectionBookedAppointments.setUserId(null);
                bookedAppointmentsCollectionBookedAppointments = em.merge(bookedAppointmentsCollectionBookedAppointments);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
public Users findByUsernameAndPassword(String username, String password) throws EntityNotFoundException {
    EntityManager em = getEntityManager();
    try {
        String queryString = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password";
        TypedQuery<Users> query = em.createQuery(queryString, Users.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("No user found with the given username and password");
        }
    } finally {
        em.close();
    }
}


public Users findByUsernameAndPasswordAndRole(String username, String password) throws EntityNotFoundException {
    EntityManager em = getEntityManager();
    try {
        String queryString = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password AND u.role = :role";
        TypedQuery<Users> query = em.createQuery(queryString, Users.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setParameter("role", "admin");

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("No user found with the given username, password, and role");
        }
    } finally {
        em.close();
    }
}

public List<Users> getDoctors() {
    EntityManager em = getEntityManager();
    try {
        String queryString = "SELECT u FROM Users u WHERE u.role = :role";
        TypedQuery<Users> query = em.createQuery(queryString, Users.class);
        query.setParameter("role", "admin");
        return query.getResultList();
    } finally {
        em.close();
    }
}

public Users getUserById(int userId) {
    EntityManager em = getEntityManager();
    try {
        return em.find(Users.class, userId);
    } finally {
        em.close();
    }
}

public void deleteUserById(int userId) {
    EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        Users user = em.find(Users.class, userId);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    } finally {
        em.close();
    }
}

}       


