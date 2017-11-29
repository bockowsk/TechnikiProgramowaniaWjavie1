package info.bockowsk;
import java.io.*;

abstract class Book implements Serializable {
    final String autor;
    final String tytul;
    final String wydawca;
    String tresc;

    Book(String a, String t, String w) {
        autor=a;
        tytul=t;
        wydawca=w;
        tresc="";
    }
}
