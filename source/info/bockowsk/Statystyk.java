package info.bockowsk;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

//generic type, ktory bedzie uzywal obiektow klay Book oraz implementowal interfejs Serializable
//
class Statystyk <T extends Book & Serializable> {

    String[] getWordsInLyrics(T t) {
        return t.tresc.split("\\W+");
    }

    HashMap<String,LinkedList<String>> createWordStatistics(String[] slowa) {
        HashMap<String,LinkedList<String>> mapa1=new HashMap<String,LinkedList<String>>();
        for (String s: slowa) {
            String litera=String.valueOf(s.charAt(0)).toLowerCase();
                if (mapa1.containsKey(litera)) {
                    mapa1.get(litera).add(s);
                }
                else {
                    mapa1.put(litera,new LinkedList<String>());
                    mapa1.get(litera).add(s);
                }
        }   
        return mapa1;
    }

    ArrayList<String> wyswietlNajczestsze(HashMap<String,LinkedList<String>> mapa1) {
        ArrayList<String> najczestsze=new ArrayList<String>();
        int maksymalna=0;
        for (String l: mapa1.keySet()) {
            int wielkosc=mapa1.get(l).size();
            if (wielkosc> maksymalna) {
                najczestsze.clear();
                najczestsze.add(l);
                maksymalna=wielkosc;
            }
            else if (wielkosc == maksymalna) {
                najczestsze.add(l);
            }
        }
        return najczestsze;
    }
    
    public static void main(String[] args) {
        Ebook ksiazka1=new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
        Ebook ksiazka2=new Ebook("Stephen Kochan", "Programming C", "Sams Publishing");
        Statystyk<Book> statystyk1=new Statystyk<Book>();
        String[] slowa=statystyk1.getWordsInLyrics(ksiazka1);
        HashMap<String,LinkedList<String>> statystyki=statystyk1.createWordStatistics(slowa);
        ArrayList<String> najczestsze=statystyk1.wyswietlNajczestsze(statystyki);
        String c1=String.format("KSIAZKA:\n\t%s\n\t%s",ksiazka1.autor,ksiazka1.tytul);
        System.out.println(c1);
        for (String i: najczestsze) {
            System.out.println(statystyki.get(i));
        }
        Statystyk<Book> statystyk2=new Statystyk<Book>();
        String[] slowa2=statystyk2.getWordsInLyrics(ksiazka2);
        HashMap<String,LinkedList<String>> statystyki2=statystyk2.createWordStatistics(slowa2);
        ArrayList<String> najczestsze2=statystyk2.wyswietlNajczestsze(statystyki2);
        String c2=String.format("KSIAZKA:\n\t%s\n\t%s",ksiazka2.autor,ksiazka2.tytul);
        System.out.println(c2);
        for (String i: najczestsze2) {
            System.out.println(statystyki2.get(i));
        }

    }

/* TODO:
 * - stworzyc comparable dla Book zeby sie dalo sortowac (na podstawie liter, ktory ma najwiecej slow z wystapieniami)
 * - zaimplementowac tutaj zapisywanie obiektow
 */
}
