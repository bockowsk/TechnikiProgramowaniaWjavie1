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

    HashMap<String,LinkedList<T>> createWordStatistics(T t, String[] slowa) {
        HashMap<String,LinkedList<T>> mapa1=new HashMap<String,LinkedList<T>>();
        for (String s: slowa) {
            String litera=String.valueOf(s.charAt(0)).toLowerCase();
                if (mapa1.containsKey(litera)) {
//blad, bo LinkedList ma Ksiazka, a nie String
                    mapa1.get(litera).add(s);
                }
                else {
                    mapa1.put(litera,new LinkedList<String>());
                    mapa1.get(litera).add(s);
                }
        }   
        return mapa1;
    }

    ArrayList<String> wyswietlNajczestsze(HashMap<String,LinkedList<T>> mapa1) {
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

/* TODO:
 * - stworzyc comparable dla Book zeby sie dalo sortowac (na podstawie liter, ktory ma najwiecej slow z wystapieniami)
 * - zaimplementowac tutaj zapisywanie obiektow
 * - stworzyc metody,ktore beda szukac jaka litera wystepuje najczesciej (generic methods) i ile razy
 * - metody do fajnego printowania (formatter)
 */
}
