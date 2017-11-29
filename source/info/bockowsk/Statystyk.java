package info.bockowsk;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

//generic type, ktory bedzie uzywal obiektow klay Book oraz implementowal interfejs Serializable
//
class Statystyk <T extends Book & Serializable> {

    static <U extends Book & Serializable> ArrayList<String> najczestsze(U t) {
        String[] slowa=t.tresc.split("\\W+");
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
        
        ArrayList<ArrayList<String>> najczestsze=new ArrayList<ArrayList<String>>();
        najczestsze.add(najczestsze(ksiazka1));najczestsze.add(najczestsze(ksiazka2));
        
        for (ArrayList<String> a: najczestsze) {
            for (String s: a) {
                System.out.println(s);
            }
        }

    }

/* TODO:
 * - stworzyc comparable dla Book zeby sie dalo sortowac (na podstawie liter, ktory ma najwiecej slow z wystapieniami)
 * - zaimplementowac tutaj zapisywanie obiektow
 */
}
