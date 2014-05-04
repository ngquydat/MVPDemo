package com.course.mvp.demo.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.course.mvp.demo.client.services.LoginService;
import com.course.mvp.demo.shared.FieldVerifier;
import com.course.mvp.demo.shared.LoginInfo;
import com.course.mvp.demo.shared.Person;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class
			.getName());
	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");
	String sql = "select from " + Person.class.getName();

	public PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}

	public Boolean register(Person user) {
		if (!FieldVerifier
				.isValidName(user.userName, user.passWord, user.email)) {
			System.out.println("Name must be at least 4 characters long");
			return false;
		} else {
			PersistenceManager pm = getPersistenceManager();
			try {
				pm.makePersistent(user);
				LOG.log(Level.INFO, pm.makePersistent(user)
						+ ": register successfully: username:  "
						+ user.userName + "logoutURL: " + user.logoutUrl);
				return true;
			} finally {
				pm.close();
			}
		}

	}

	public Person login(Person user) {

		PersistenceManager pm = getPersistenceManager();
		try {
			Person result = new Person();
			List<Person> users = (List<Person>) pm.newQuery(sql).execute();
			for (Person useri : users) {
				System.out.println(useri.userName);
				if (user.userName.equals(useri.userName)
						&& user.passWord.equals(useri.passWord)) {
					LOG.log(Level.INFO, user.userName + " have logged in");
					pm.currentTransaction().begin();
					useri.loggedIn = true;
					pm.makePersistent(useri);
					pm.currentTransaction().commit();
					// Person user1 = pm.getObjectById(Person.class,
					// useri.id);
					// System.out.println(user1.loggedIn);
					result = useri;
				}
			}
			return result;
		} finally {
			pm.close();
		}

	}

	public Boolean logout(long userid) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.currentTransaction().begin();
			Person user = pm.getObjectById(Person.class, userid);
			user.loggedIn = false;
			pm.makePersistent(user);
			pm.currentTransaction().commit();
			LOG.log(Level.INFO, user.userName + "have logged out");
			return true;
		} finally {
			pm.close();
		}

	}

	public Person getUser(long userid) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Person user = pm.getObjectById(Person.class, userid);
			return user;
		} finally {
			pm.close();
		}
	}

	public Boolean delUser(long userid) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Person user = pm.getObjectById(Person.class, userid);
			pm.deletePersistent(user);
			return true;
		} finally {
			pm.close();
		}
	}

	public List<Person> getAll() {
		PersistenceManager pm = getPersistenceManager();
		List<Person> listUsers = new ArrayList<Person>();
		try {
			listUsers = (List<Person>) pm.newQuery(sql).execute();
			return listUsers;
		} finally {
			pm.close();
		}
	}

	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		if (user != null) {
			loginInfo.loggedIn = true;
			loginInfo.email = user.getEmail();
			loginInfo.nickname = user.getNickname();
			loginInfo.logoutUrl = userService.createLogoutURL(requestUri);
			loginInfo.id = user.getUserId();
		} else {
			loginInfo.loggedIn = false;
			loginInfo.loginUrl = userService.createLoginURL(requestUri);
		}
		return loginInfo;
	}

}