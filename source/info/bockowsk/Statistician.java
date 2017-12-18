package info.bockowsk;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

//generic type, ktory bedzie uzywal obiektow klay Book oraz implementowal interfejs Comparable 
//
class Statistician<T extends Book & Comparable<? extends Book>> {

	static <U extends Book & Comparable<?extends Book>> HashMap<String, LinkedList<String>> theMostOften(U u) {
		String[] words = u.content.split("\\W+");
		HashMap<String, LinkedList<String>> mapa1 = new HashMap<String, LinkedList<String>>();
		for (String word : words) {
			String letter = String.valueOf(word.charAt(0)).toLowerCase();
			if (mapa1.containsKey(letter)) {
				mapa1.get(letter).add(word);
			} else {
				mapa1.put(letter, new LinkedList<String>());
				mapa1.get(letter).add(word);
			}
		}
		ArrayList<String> theMostOften = new ArrayList<String>();
		int maximum = 0;
		for (String l : mapa1.keySet()) {
			int size = mapa1.get(l).size();
			if (size > maximum) {
				theMostOften.clear();
				theMostOften.add(l);
				maximum = size;
			} else if (size == maximum) {
				theMostOften.add(l);
			}
		}
		HashMap<String, LinkedList<String>> map2 = new HashMap<String, LinkedList<String>>();
		for (String word : theMostOften) {
			map2.put(u.title + ": " + word, mapa1.get(word));
		}
		return map2;
	}

	public static void main(String[] args) {
		Ebook book1 = new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
		Ebook book2 = new Ebook("Stephen Kochan", "Programming C", "Sams Publishing");
		Ebook book3 = new Ebook("Randal Schwartz", "Learning Perl", "O'Reilly");
		Ebook book4 = new Ebook("Olimp Bockowski", "Plagiat", "w piwnicy");

		ArrayList<HashMap<String, LinkedList<String>>> theMostOften = new ArrayList<HashMap<String, LinkedList<String>>>();
		theMostOften.add(theMostOften(book1));
		theMostOften.add(theMostOften(book2));
		theMostOften.add(theMostOften(book3));
		theMostOften.add(theMostOften(book4));

		for (HashMap<String, LinkedList<String>> m : theMostOften) {
			for (String letter : m.keySet()) {
				System.out.println(letter);
				System.out.println("\t" + m.get(letter));
			}
		}
		ArrayList<Ebook> all = new ArrayList<Ebook>();
		System.out.println("All books not sorted");
		all.add(book1);
		all.add(book2);
		all.add(book3);
		all.add(book4);

		for (Ebook ebook : all) {
			System.out.println("\t" + ebook);
		}
		System.out.println("All books sorted.");
		Collections.sort(all);
		for (Ebook ebook : all) {
			System.out.println("\t" + ebook);
		}
		// duplikaty
		HashSet<Ebook> set1 = new HashSet<Ebook>();
		for (Ebook ebook : all) {
			if (!set1.add(ebook)) {
				for (Ebook ebook2 : all) {
					if (ebook.hashCode() == ebook2.hashCode() && ebook.equals(ebook2) && !(ebook.title.equals(ebook2.title))) {
						System.out.println(ebook.author + ":" + ebook.title + " , " + ebook2.author + ":" + ebook2.title);
					}
				}
			}
		}
	}
}
