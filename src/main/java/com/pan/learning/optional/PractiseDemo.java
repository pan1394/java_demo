package com.pan.learning.optional;

import java.util.Optional;

public class PractiseDemo {

	public static void main(String[] args) {
		Person p = null;
		Optional<Person> optPerson = Optional.ofNullable(p);
		Optional<Optional<Car>> optCar = optPerson.map(Person::getCar);
		optPerson.flatMap( pe-> pe.getCar());
		/*Insurance insurance = new Insurance();
		Optional<Insurance> optIn = Optional.of(insurance);
		String value = optIn.map(Insurance::getName).orElse("unknown");*/

		System.out.println(optCar);
	}
}
