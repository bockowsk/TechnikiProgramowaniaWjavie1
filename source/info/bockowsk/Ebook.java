package info.bockowsk;

import java.io.*;

class Ebook extends Book implements Comparable<Ebook> {

    Ebook(String a, String t, String w) {
        super(a,t,w);
        String nazwa=a+"-"+t+".txt";
        File plik1=new File(nazwa);
        String linia;
        try {
            FileReader fileReader1=new FileReader(plik1);
            BufferedReader reader1=new BufferedReader(fileReader1);
            while ((linia=reader1.readLine())  != null ) {
                super.tresc+=linia;
            }
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
    public int compareTo(Ebook b) {
        int b1=tresc.length();
        int b2=b.tresc.length();
        if (b1>b2) {
            return -1;
        }
        else if (b1<b2) {
            return 1;
        }
        else {
            return 0;
        }
    }
    public String toString() {
        return autor+" : "+tytul;
    }
    public static void main(String args[]) {
        Ebook ksiazka1=new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
        System.out.println(ksiazka1.tresc);
    }
}
