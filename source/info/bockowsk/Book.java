package info.bockowsk;

import java.io.*;

abstract class Book implements Serializable {
	private static final long serialVersionUID = 6249798813757094085L;
	final String author;
	final String title;
	final String publisher;
	String content;

	Book(String a, String t, String w) {
		author = a;
		title = t;
		publisher = w;
		content = "";
	}
}
