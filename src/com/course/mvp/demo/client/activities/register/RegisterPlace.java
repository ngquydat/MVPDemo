package com.course.mvp.demo.client.activities.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends Place {
	String token = "";
	public RegisterPlace() {
		super();
		token = "Login";
	}

	public String getToken() {
		return this.token;
	}
	public static class Tokenizer implements PlaceTokenizer<RegisterPlace> {
        @Override
        public String getToken(RegisterPlace place) {
            return place.getToken();
        }

        @Override
        public RegisterPlace getPlace(String token) {
            return new RegisterPlace();
        }
    }	
}
