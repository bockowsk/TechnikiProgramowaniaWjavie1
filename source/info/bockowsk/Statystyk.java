package info.bockowsk;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

//generic type, ktory bedzie uzywal obiektow klay Book oraz implementowal interfejs Comparable 
//
class Statystyk <T extends Book & Comparable> {

    static <U extends Book & Comparable > HashMap<String,LinkedList<String>> najczestsze(U u) {
        String[] slowa=u.tresc.split("\\W+");
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
        HashMap<String,LinkedList<String>> mapa2=new HashMap<String,LinkedList<String>>();
        for (String s: najczestsze) {
            mapa2.put(u.tytul+": "+s,mapa1.get(s));
        }
        return mapa2;
    }
    
    public static void main(String[] args) {
        Ebook ksiazka1=new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
        Ebook ksiazka2=new Ebook("Stephen Kochan", "Programming C", "Sams Publishing");
        Ebook ksiazka3=new Ebook("Randal Schwartz","Learning Perl","O'Reilly");
        Ebook ksiazka4=new Ebook("Olimp Bockowski","Plagiat","w piwnicy");
        
        ArrayList<HashMap<String,LinkedList<String>>> najczestsze=new ArrayList<HashMap<String,LinkedList<String>>>();
        najczestsze.add(najczestsze(ksiazka1));najczestsze.add(najczestsze(ksiazka2));
        najczestsze.add(najczestsze(ksiazka3));najczestsze.add(najczestsze(ksiazka4));


        for (HashMap<String,LinkedList<String>> m: najczestsze) {
            for (String s: m.keySet()) {
                System.out.println(s);
                System.out.println("\t"+m.get(s));
            }
        }
        ArrayList<Ebook> wszystkie=new ArrayList<Ebook>();
        System.out.println("Wszystkie ksiazki nieposortowane.");
        wszystkie.add(ksiazka1);wszystkie.add(ksiazka2);wszystkie.add(ksiazka3);wszystkie.add(ksiazka4);

        for (Ebook e: wszystkie) {
            System.out.println("\t"+e);
        }
        System.out.println("Wszystkie ksiazki po sortowaniu.");
        Collections.sort(wszystkie);
        for (Ebook e: wszystkie) {
            System.out.println("\t"+e);
        }
//duplikaty
        HashSet<Ebook> zbior1=new HashSet<Ebook>();
        for (Ebook b: wszystkie) {
            if (!zbior1.add(b)) {
                for (Ebook b2: wszystkie) {
                    if (b.hashCode() == b2.hashCode() && b.equals(b2) && !(b.tytul.equals(b2.tytul))) {
                        System.out.println(b.autor+":"+b.tytul+" , "+b2.autor+":"+b2.tytul);
                    }
                }
            }
        }
    }
}
