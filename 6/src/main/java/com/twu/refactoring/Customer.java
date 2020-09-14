package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			// determine amounts for each line
			thisAmount = getThisAmount(thisAmount, each);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double getThisAmount(double thisAmount, Rental each) {
		switch (each.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			thisAmount = judgeCase(thisAmount, each, 2,2,1.5);
			break;
			case Movie.NEW_RELEASE:
			thisAmount += each.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			thisAmount = judgeCase(thisAmount, each,1.5,3,1.5);
			break;

		}
		return thisAmount;
	}

	private double judgeCase(double thisAmount, Rental each, double amount, double dayRent, double plusNum) {
		thisAmount += amount;
		if (each.getDaysRented() > dayRent)
			thisAmount += (each.getDaysRented() - dayRent) * plusNum;
		return thisAmount;
	}

}
