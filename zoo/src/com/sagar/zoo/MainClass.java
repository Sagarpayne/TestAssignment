
package com.sagar.zoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MainClass {

	public static void main(String[] args) {

		int numberOfDays = 0;
		try {

			numberOfDays = Integer.parseInt(args[0]);
		} catch (Exception ex) {
			numberOfDays = 2;
		}

		System.out.println("===== Welcome to ZOO =====");

		ArrayList<Animal> animalsInZoo = new ArrayList<Animal>();

		animalsInZoo.add(new Dog(DogBreed.HUNTING_DOG, "Dog One", "Meat"));
		animalsInZoo.add(new Dog(DogBreed.ASSISTANCE_DOG, "Dog Two", "Fresh Meat"));
		animalsInZoo.add(new Dog(DogBreed.RACING_DOG, "Dog Three", "Pedigree"));

		animalsInZoo.add(new Parrot(false, 0.25F, "Parrot One", "Grain"));
		animalsInZoo.add(new Parrot(false, 0.5F, "Parrot Two", "Corn"));

		animalsInZoo.add(new Chicken(false, 0.75F, "Chicken One", "Grain"));
		animalsInZoo.add(new Chicken(false, 0.75F, "Chicken Two", "Corn"));

		System.out.println("=##= All Animals =##= ");
		displayInitial(animalsInZoo);

		// addNewFriend(dogs,parrots,chickens);
		System.out.println("==== Initial List ====");
		initFriends(animalsInZoo);
		display(animalsInZoo);

		for (int i = 0; i <= numberOfDays; i++) {
			System.out.println("==== Day "+ i + " Start ====");
			addNewFriend(animalsInZoo);
			display(animalsInZoo);
			System.out.println("==== Day "+ i + " Finished ====");
		}

	}

	static void displayInitial(ArrayList<Animal> list) {

		list.forEach(animal -> System.out.println(animal.toString()));

	}

	static void display(ArrayList<Animal> list) {

		list.forEach(animal -> System.out.println(animal.DisplayAnimal()));

	}

	static void addNewFriend(ArrayList<Animal> animalList) {

		for (Animal animal : animalList) {

			String nameOfAnimal = animal.name;
			for (Animal friend : animalList) {

				if (!animal.friendList.contains((friend.name)) && !friend.name.equals(nameOfAnimal)) {

					Random randomBool = new Random();

					if (randomBool.nextBoolean()) {
						if(animal.addNewFriend(friend.name)){ 
						friend.addNewFriend(nameOfAnimal);
						break;
						}
					}
				}

			}
		}
	}

	static void initFriends(ArrayList<Animal> animalList) {

		for (Animal animal : animalList) {

			String nameOfAnimal = animal.name;
			for (Animal friend : animalList) {

				if (!animal.friendList.contains((friend.name)) && !friend.name.equals(nameOfAnimal)) {

					Random randomBool = new Random();

					if (randomBool.nextBoolean()) {
						animal.friendList.add(friend.name);
						friend.friendList.add(nameOfAnimal);
					}
				}

			}
		}
	}

}

class Animal {

	protected String name;
	protected String favoriteFood;
	protected ArrayList<String> friendList;

	Animal() {
		this.friendList = new ArrayList<String>();
	}

	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; } public String getFavoriteFood() { return favoriteFood; }
	 * public void setFavoriteFood(String favoriteFood) { this.favoriteFood =
	 * favoriteFood; }
	 */

	boolean addNewFriend(String friend) {

		if (this.friendList.size() > 0) {

			if (!this.friendList.contains(friend)) {
				this.friendList.add(friend);
				this.removeOldFriend();
				return true;
			}
			else
				return false;
		} else {
			this.friendList.add(friend);
			this.removeOldFriend();
			return true;
		}

	}

	void removeOldFriend() {
		int countOfFriends = this.friendList.size();

		if (countOfFriends > 0) {

			int random = (int) (0 + Math.floor(Math.random() * countOfFriends));

			this.friendList.remove(this.friendList.get(random));
		}
	}

	public String DisplayAnimal() {
		return "Animal [name=" + name + ", friendList=" + friendList + "]";
	}

}

enum DogBreed {
	RACING_DOG, HUNTING_DOG, ASSISTANCE_DOG
}

class Dog extends Animal {

	DogBreed dogType;

	public Dog(DogBreed dogbreed, String name, String favoriteFood) {
		super();
		this.dogType = dogbreed;
		this.name = name;
		this.favoriteFood = favoriteFood;

	}

	@Override
	public String toString() {
		return "Dog [dogType=" + dogType + ", name=" + name + ", favoriteFood=" + favoriteFood + ", friendList="
				+ friendList + "]";
	}

}

class Parrot extends Animal {

	boolean canSpeak;
	float wingSpan;

	public Parrot(boolean canSpeak, float wingSpan, String name, String favoriteFood) {
		// super();
		this.canSpeak = canSpeak;
		this.wingSpan = wingSpan;
		this.name = name;
		this.favoriteFood = favoriteFood;

	}

	@Override
	public String toString() {
		return "Parrot [canSpeak=" + canSpeak + ", wingSpan=" + wingSpan + ", name=" + name + ", favoriteFood="
				+ favoriteFood + ", friendList=" + friendList + "]";
	}

}

class Chicken extends Animal {

	boolean isBroiler;
	float wingSpan;

	public Chicken(boolean isBroiler, float wingSpan, String name, String favoriteFood) {
		// super();
		this.isBroiler = isBroiler;
		this.wingSpan = wingSpan;
		this.name = name;
		this.favoriteFood = favoriteFood;

	}

	@Override
	public String toString() {
		return "Chicken [isBroiler=" + isBroiler + ", wingSpan=" + wingSpan + ", name=" + name + ", favoriteFood="
				+ favoriteFood + ", friendList=" + friendList + "]";
	}

}