package ru.otus.example.beanslifecycledemo.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@ConditionalOnProperty(name = "lifecycle.print.enabled", havingValue = "false")
@RequiredArgsConstructor
public class Phone {
	private final String greeting = "Погнали к родителям";

	private PhoneNumber favoriteNumber;

	public void callFavoriteNumber() {
		System.out.println(favoriteNumber.getOwnerName() + " " + greeting);
	}
}
