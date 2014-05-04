package com.course.mvp.demo.client.activities.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place {
	String token = "";
	public LoginPlace() {
		super();
		token = "Login";
	}

	public String getToken() {
		return this.token;
	}
	public static class Tokenizer implements PlaceTokenizer<LoginPlace> {
        @Override
        public String getToken(LoginPlace place) {
            return place.getToken();
        }

        @Override
        public LoginPlace getPlace(String token) {
            return new LoginPlace();
        }
    }	
}
