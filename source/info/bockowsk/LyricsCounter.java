package info.bockowsk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class LyricsCounter {

    private static final String LYRICS = "Dwunastu braci, wierzac w sny, zbadalo mur od marzen strony,\n" +
            "A poza murem plakal glos, dziewczecy glos zaprzepaszczony.\n" +
            "I pokochali glosu dzwiek i chetny domysl o Dziewczynie,\n" +
            "I zgadywali ksztalty ust po tym, jak spiew od zalu ginie...\n" +
            "Mowili o niej: \"lka, wiec jest!\" - I nic innego nie mowili,\n" +
            "I przezegnali caly swiat - i swiat zadumal sie w tej chwili...\n" +
            "Porwali mloty w twarda dlon i jeli w mury tluc z loskotem!\n" +
            "I nie wiedziala slepa noc, kto jest czlowiekiem, a kto mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty brat do jedenastu innych rzecze.\n" +
            "Ale daremny byl ich trud, daremny ramion sprzeg i usil!\n" +
            "Oddali ciala swe na strwon owemu snowi, co ich kusil!\n" +
            "lamia sie piersi, trzeszczy kosc, prochnieja dlonie, twarze bledna...\n" +
            "I wszyscy w jednym zmarli dniu i noc wieczysta mieli jedna!\n" +
            "Lecz cienie zmarlych - Boze moj! - nie wypuscily mlotow z dloni!\n" +
            "I tylko inny plynie czas - i tylko mlot inaczej dzwoni...\n" +
            "I dzwoni w przod! I dzwoni wspak! I wzwyz za kazdym grzmi nawrotem!\n" +
            "I nie wiedziala slepa noc, kto tu jest cieniem, a kto mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty cien do jedenastu innych rzecze.\n" +
            "Lecz cieniom zbraklo nagle sil, a cien sie mrokom nie opiera!\n" +
            "I powymarly jeszcze raz, bo nigdy dosc sie nie umiera...\n" +
            "I nigdy dosc, i nigdy tak, jak pragnie tego ow, co kona!...\n" +
            "I znikla tresc - i zginal slad - i powiesc o nich juz skonczona!\n" +
            "Lecz dzielne mloty - Boze moj - mdlej nie poddaly sie zalobie!\n" +
            "I same przez sie bily w mur, huczaly spizem same w sobie!\n" +
            "Huczaly w mrok, huczaly w blask i ociekaly ludzkim potem!\n" +
            "I nie wiedziala slepa noc, czym bywa mlot, gdy nie jest mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty mlot do jedenastu innych rzecze.\n" +
            "I runal mur, tysiacem ech wstrzasajac wzgorza i doliny!\n" +
            "Lecz poza murem - nic i nic! Ni zywej duszy, ni Dziewczyny!\n" +
            "Niczyich oczu ani ust! I niczyjego w kwiatach losu!\n" +
            "Bo to byl glos i tylko - glos, i nic nie bylo oprocz glosu!\n" +
            "Nic - tylko placz i zal i mrok i niewiadomosc i zatrata!\n" +
            "Takiz to swiat! Niedobry swiat! Czemuz innego nie ma swiata?\n" +
            "Wobec klamliwych jawnie snow, wobec zmarnialych w nicosc cudow,\n" +
            "Potezne mloty legly w rzad, na znak spelnionych godnie trudow.\n" +
            "I byla zgroza naglych cisz. I byla proznia w calym niebie!\n" +
            "A ty z tej prozni czemu drwisz, kiedy ta proznia nie drwi z ciebie?";

    private static String[] getWordsInLyrics() {
        return LYRICS.split("\\W+");
    }

    public static void main(String[] args) {
        HashMap<String, LinkedList<String>> wordBegins = new HashMap<>();
        // pobranie slow
        String[] slowa=getWordsInLyrics();
        //nie jest powiedzane czy case insensitive, ale zakladam, ze tak, tutaj moznaby bylo sie tym zajac, ale bedzie to zrobione nizej
        // stworzenie mapy z keys jako pierwsza litera, a value bedace nested collection (e.g. LinkedList)
        for (String s: slowa) {
            // tutaj zajmujemy sie wielkoscia liter
            String litera=String.valueOf(s.charAt(0)).toLowerCase();
            //moznaby bylo wykozystac preinitialization i stworzyc HashMap ze wszystkimi literami, ale bedzie to ad-hoc
            // dla juz istniejacych
            if (wordBegins.containsKey(litera)) {
                wordBegins.get(litera).add(s);
            }
            //jesli nie ma to tworzy LinkedList i dodaje pierwszy element
            else {
                wordBegins.put(litera,new LinkedList<String>());
                wordBegins.get(litera).add(s);
            }
        }
        //wyszukanie liter/litery z najwieksza liczba wyrazow (bo przeciez moze byc wiecej niz 1)
        ArrayList<String> najczestsze=new ArrayList<String>();
        //zmienna do testow
        int maksymalna=0;
        for (String l: wordBegins.keySet()) {
            int wielkosc=wordBegins.get(l).size();
            //porownanie wielkosci
            if (wielkosc> maksymalna) {
                // czyscimy, dodajemy nowa, zmieniamy wartosc maksymalnej liczby wyrazow
                najczestsze.clear();
                najczestsze.add(l);
                //update maksymalnej
                maksymalna=wielkosc;
            }
            // uwzglednienie warunka, gdy jest taka sama liczba wystapien
            else if (wielkosc == maksymalna) {
                // po prostu dodajemy kolejna litere
                najczestsze.add(l);
            }
        }
        //wyswietlenie wyrazow dla litery z najwieksza liczba powtorzen
        for (String s: najczestsze) {
            System.out.println("Litera na ktora najczesciej zaczynaja sie slowa:");
            System.out.println(wordBegins.get(s));
            System.out.println("---");
        }
        //opcjonalne, dla testu. Wyswietlanie wszystkich liter z wystepujacmi wyrazami
        for (String s: wordBegins.keySet()) {
            int rozmiar=wordBegins.get(s).size();
            String doWyswietlenia=String.format("LITERA:\t%s\t WYSTAPIEN:\t%d\nWSZYSTKIE:\n%s\n\n",s,rozmiar,wordBegins.get(s));
            System.out.print(doWyswietlenia);
        }
    }
}

