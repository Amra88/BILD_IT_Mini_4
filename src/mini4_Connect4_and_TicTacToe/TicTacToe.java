package mini4_Connect4_and_TicTacToe;

import java.util.InputMismatchException;

public class TicTacToe {

	public static void main(String[] args) {
		// kreiramo novu matricu
		char[][] grid = new char[3][3];
		// printamo tabelu
		printGrid(grid);
		// uslov za izvrsavanje petlje
		// dok u tabeli ima praznih celija
		while (!isFilled(grid)) {
			// korisnik X unosi koordinate za upis novog X znaka
			// nakon unosa, printamo matricu
			printGrid(userInputX(grid));
			// provjeravamo da li su ispunjeni uslovi za nastavak izvrsavanja
			// programa
			if (checkRows(grid) || checkColumns(grid) || checkDiagonal(grid)) {
				// ukoliko je neka od metoda vratila vrijednost true, ispisujemo
				// poruku i program zavrsava
				System.out.println(checkRows(grid) || checkColumns(grid)
						|| checkDiagonal(grid));
			} else if (isFilled(grid)) {
				// ukoliko nema vise slobodnih mjesta u tabeli, rezultat je
				// nerijesen
				// ispisujemo poruku i izlazimo iz programa
				System.out.println("Draw! No winner!");
				System.exit(1);
			} else {
				// u suprotnom, program nastavlja sa radom
				// pozivamo metode i ponavljamo postupak, s tim sto sada
				// korisnik O unosi koordinate za upis novog O znaka
				printGrid(userInputO(grid));
				if (checkRows(grid) || checkColumns(grid)
						|| checkDiagonal(grid)) {
					System.out.println(checkRows(grid) || checkColumns(grid)
							|| checkDiagonal(grid));
				} else if (isFilled(grid)) {
					System.out.println("Draw! No winner!");
					break;
				}
			}
		}
	}

	// Metoda za unos podataka od korisnika X
	// Metodi prosljedjujemo matricu (tabelu) za igranje tic-tac-toe igre
	// Metoda vraca izmijenjenu matricu, u koju dodajemo novi unos od korisnika
	// X
	public static char[][] userInputX(char[][] grid) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		boolean c = true;// uslov za izvrsavanje petlje
		int[] cell = new int[2];// niz u koji spremamo indekse (koordinate) za
								// novi "X"
		while (c) {
			try {
				// korisnik unosi zeljeni indeks reda i kolone
				System.out.println("Enter a row (0, 1, or 2) for player X:");
				int row = input.nextInt();
				System.out.println("Enter a column (0, 1, or 2) for player X:");
				int column = input.nextInt();
				// koordinate spremamo u niz
				cell[0] = row;
				cell[1] = column;
				// provjeravamo da li je trazena celija tabele slobodna za unos
				if (grid[cell[0]][cell[1]] != '\u0000') {
					// ukoliko nije, korisnik unosi nove koordinate
					System.out
							.println("The cell is not available! Try again. ");
					c = true;

				} else {
					// u suprotnom, u trazenu celiju spremamo X
					grid[cell[0]][cell[1]] = 'X';
					c = false;
				}

			} catch (IndexOutOfBoundsException ex) {
				// metoda hvata IndexOutOfBoundsException u slucaju da korisnik
				// unese indeks niza izvan dometa
				System.out.println("\nEnter 0, 1, or 2 for row or column: ");
			}
		}
		return grid;// metoda vraca matricu sa spremljenim unosom korisnika X
	}

	// Metoda za unos podataka od korisnika O
	// Metoda ima istu funkciju i naredbe kao i prethodna metoda, s tim sto
	// ovdje uzimamo unos od korisnika O, odnosno u tabelu upisujemo "O"
	public static char[][] userInputO(char[][] grid) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		boolean c = true;
		int[] cell = new int[2];
		while (c) {
			try {
				System.out.println("Enter a row (0, 1, or 2) for player O:");
				int row = input.nextInt();
				System.out.println("Enter a column (0, 1, or 2) for player O:");
				int column = input.nextInt();
				cell[0] = row;
				cell[1] = column;
				if (grid[cell[0]][cell[1]] != 0) {
					System.out
							.println("The cell is not available! Try again. ");
					c = true;

				} else {
					grid[cell[0]][cell[1]] = 'O';
					c = false;
				}

			} catch (IndexOutOfBoundsException ex) {
				System.out.println("\nEnter 0, 1, or 2 for row or column: ");
			}
		}
		return grid;
	}

	// Metoda provjerava da li u nekom redu postoje tri ista znaka, X ili O (da
	// li je neki od korisnika pobijedio)
	// Metodi prosljedjujemo matricu unesenih karaktera
	public static boolean checkRows(char[][] grid) {
		boolean result = false;// rezultat je postavljen na false; u ovom
								// slucaju, program ce nastaviti sa radom
		int x = 0;// brojac X znaka
		int o = 0;// brojac O znaka
		for (int i = 0; i < grid.length; i++) {
			// pri svakoj iteraciji brojace postavljamo na 0
			x = 0;
			o = 0;
			// ako u nekom redu naidjemo na X ili O, brojace uvecavamo
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 'X') {
					x++;
				} else if (grid[i][j] == 'O') {
					o++;
				}
			}
			// ako pronadjemo tri ista znaka, vracamo boolean vrijednost true,
			// ispisujemo poruku, i izlazimo iz programa
			// kada je rezultat true, program zavrsava sa radom
			if (x == 3) {
				System.out.println("Player X wins!");
				result = true;
				System.exit(1);
			} else if (o == 3) {
				System.out.println("Player O wins!");
				result = true;
				System.exit(1);
			} else {
				// u suprotnom boolean varijabla je false
				result = false;
			}
		}
		return result;// metoda vraca vrijednost true ili false
	}

	// Metoda provjerava da li u nekoj koloni postoje tri ista znaka, X ili O
	// Metodi prosljedjujemo matricu unesenih karaktera
	// U metodi ponavljamo postupak kao i u prethodnoj, s tim sto ovdje
	// provjeravamo kolone
	public static boolean checkColumns(char[][] grid) {
		boolean result = false;
		int x = 0;
		int o = 0;
		for (int i = 0; i < grid.length; i++) {
			x = 0;
			o = 0;
			for (int j = 0; j < grid[i].length; j++) {
				// provjeravamo da li u kolonama postoje tri ista znaka, X ili O
				if (grid[j][i] == 'X') {
					x++;
				} else if (grid[j][i] == 'O') {
					o++;
				}
			}
			if (x == 3) {
				System.out.println("Player X wins!");
				result = true;
				System.exit(1);
			}
			if (o == 3) {
				System.out.println("Player O wins!");
				result = true;
				System.exit(1);
			} else {
				result = false;
			}
		}
		return result;
	}

	// Metoda provjerava da li u nekoj dijagonali postoje tri ista znaka, X ili
	// O
	// Metodi prosljedjujemo matricu unesenih karaktera
	// Metoda radi isto sto i prethodne dvije, s tim sto ovdje provjeravamo
	// dvije dijagonale
	public static boolean checkDiagonal(char[][] grid) {
		boolean result = false;
		int x = 0;
		int o = 0;
		for (int i = 0; i < grid.length; i++) {
			// s obzirom da je kod glavne dijagonale i=j, koristimo jednu petlju
			// da provjerimo da li se u ovoj dijagonali nalaze tri znaka X ili
			// tri znaka O
			if (grid[i][i] == 'X') {
				x++;
			} else if (grid[i][i] == 'O') {
				o++;
			}
		}
		if (x == 3) {
			System.out.println("Player X wins!");
			result = true;
			System.exit(1);
		}
		if (o == 3) {
			System.out.println("Player O wins!");
			result = true;
			System.exit(1);
		} else {
			result = false;
		}
		x = 0;
		o = 0;
		for (int j = 0; j < grid.length; j++) {
			// za sporednu dijagonalu takodje koristimo jednu petlju
			// kroz ovu dijagonalu prolazimo koristeci se formulom i+j=n-1
			if (grid[j][grid.length - 1 - j] == 'X') {
				x++;
			} else if (grid[j][grid.length - 1 - j] == 'O') {
				o++;
			}

			if (x == 3) {
				System.out.println("Player X wins!");
				result = true;
				System.exit(1);
			}
			if (o == 3) {
				System.out.println("Player O wins!");
				result = true;
				System.exit(1);
			} else {
				result = false;
			}
		}
		return result;
	}

	// Metoda koja provjerava da li su svim elementima matrice dodijeljene
	// vrijednosti, odnosno, da li su sve celije tabele popunjene
	// Metodi prosljedjujemo matricu
	public static boolean isFilled(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// ako je neki element matrice jednak default vrijednosti char
				// tipa
				if (grid[i][j] == '\u0000') {
					return false;// metoda vraca false
				}
			}
		}
		// u suprotnom vraca true
		return true;
	}

	// Metoda za ispis tabele
	public static void printGrid(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			// ispis horizontalne linije
			System.out.println("-------------------");

			for (int j = 0; j < grid[i].length; j++) {

				if (j == 0) {
					// ispis elemenata i vertikalnih linija
					System.out.print("|  " + grid[i][j] + "  |");
				} else {
					System.out.print("  " + grid[i][j] + "  |");
				}
			}
			// prelazak u novi red
			System.out.println("");
		}
		System.out.println("-------------------");
	}
}
