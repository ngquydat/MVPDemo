package com.course.mvp.demo.client.services;

import java.util.List;

import com.course.mvp.demo.shared.LoginInfo;
import com.course.mvp.demo.shared.Person;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public LoginInfo login(String requestUri);

	Boolean register(Person user);

	Person login(Person User);

	Boolean logout(long userid);

	Person getUser(long userid);

	Boolean delUser(long userid);

	List<Person> getAll();
}