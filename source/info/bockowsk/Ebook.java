package info.bockowsk;

import java.io.*;

class Ebook extends Book implements Comparable<Ebook> {

	private static final long serialVersionUID = -7846172279586434651L;
	
	Ebook(String author, String title, String publisher) {
		super(author, title, publisher);
		String nazwa = author + "-" + title + ".txt";
		File file1 = new File(nazwa);
		String line;
		try {
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader reader1 = new BufferedReader(fileReader1);
			while ((line = reader1.readLine()) != null) {
				super.content += line;
			}
			reader1.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}
	}

	public int compareTo(Ebook b) {
		int b1 = content.length();
		int b2 = b.content.length();
		if (b1 > b2) {
			return -1;
		} else if (b1 < b2) {
			return 1;
		} else {
			return 0;
		}
	}

	public String toString() {
		return author + " : " + title;
	}

	public int hashCode() {
		return content.hashCode();
	}

	public boolean equals(Object ebook) {
		Ebook e = (Ebook) ebook;
		return content.equals(e.content);
	}

	public static void main(String args[]) {
		Ebook book1 = new Ebook("Mark Lutz", "Wprowadzenie Python", "Helion");
		System.out.println(book1.content);
	}
}
