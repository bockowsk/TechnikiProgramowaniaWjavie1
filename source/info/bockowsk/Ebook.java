package info.bockowsk;

import java.io.*;

class Ebook extends Book {

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
    public static void main(String args[]) {
        Ebook ksiazka1=new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
        System.out.println(ksiazka1.tresc);
    }
}
