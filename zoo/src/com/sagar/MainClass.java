
package com.sagar;

import java.util.ArrayList;
import java.util.Random;

public class MainClass {

	static ArrayList<Animal> animalsInZoo = new ArrayList<Animal>();

	public static void main(String[] args) {

		int numberOfDays = 0;
		try {

			numberOfDays = Integer.parseInt(args[0]);
		} catch (Exception ex) {
			numberOfDays = 1;
		}

		System.out.println("===== Welcome to ZOO =====");

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
		System.out.println("\n==== Initial List ====");
		initFriends();
		display(animalsInZoo);

		for (int i = 1; i <= numberOfDays; i++) {
			System.out.println("\n==== Day " + i + " Start ====");
			removeFriend();
			addNewFriend();
			System.out.println("==== Day " + i + " Finished ====\n");

			display(animalsInZoo);
		}

	}

	static void displayInitial(ArrayList<Animal> list) {

		list.forEach(animal -> System.out.println(animal.toString()));

	}

	static void display(ArrayList<Animal> list) {

		list.forEach(animal -> System.out.println(animal.DisplayAnimal()));

	}

	static void removeFriend() {
		for (Animal animal : animalsInZoo) {

			// System.out.println("Animal Name :" + animal.name);

			// animal.friendList.forEach(x -> System.out.println(x));

			if (animal.friendList.size() > 0) {
				int random = (int) (0 + Math.floor(Math.random() * animal.friendList.size()));
				String removedFriend = animal.friendList.get(random);
				animal.friendList.remove(removedFriend);

				for (Animal removedanimal : animalsInZoo) {

					if (removedanimal.name.equals(removedFriend) && removedanimal.friendList.size() > 0) {
						for (int i = 0; i < removedanimal.friendList.size(); i++) {
							String tempName = removedanimal.friendList.get(i);
							if (tempName.equals(animal.name)) {
								removedanimal.friendList.remove(i);
							}
						}
					}
				}

				animalsInZoo.get(random).friendList.remove(animal.name);
				System.out.println(animal.name + " broke friends with " + removedFriend);
			}
		}
	}

	static void addNewFriend() {

		for (Animal animal : animalsInZoo) {
			int countAnimals = 0;
			String nameOfAnimal = animal.name;
			for (Animal friend : animalsInZoo) {
				
				countAnimals+=1;
				if (!animal.friendList.contains((friend.name)) && !friend.name.equals(nameOfAnimal)) {

					Random randomBool = new Random();
					if (randomBool.nextBoolean() || countAnimals <= animalsInZoo.size() ) {

						System.out.println(animal.name + " became friends with " + friend.name);
						animal.friendList.add(friend.name);
						friend.friendList.add(nameOfAnimal);

						break;
					}
				}

			}
		}

	}

	static void initFriends() {

		for (Animal animal : animalsInZoo) {

			String nameOfAnimal = animal.name;
			for (Animal friend : animalsInZoo) {

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