
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import dao.AvionDAO;
import dao.ReservationDAO;
import model.Avion;
import model.Reservation;
import model.Vol;

public class Run {

	private static Scanner sc = new Scanner(System.in);

	static boolean continuer = true;

	static int choixMenu = 0;

	static AvionDAO dao = new AvionDAO();

	static ReservationDAO dao1 = new ReservationDAO();

	public static void main(String[] args) {

		while (continuer) {

			SelectFirstMenu();
		}
	}

	public static void SelectFirstMenu() {

		System.out.println("--------------------------------");

		System.out.println("\t MENU PRINCIPALE");

		System.out.println("--------------------------------");

		System.out.println("1) Gestion des vols");

		System.out.println("2) Gestion des réservations");

		System.out.println("3) Quitter");

		System.out.println("Entrez votre choix :");

		choixMenu = Integer.parseInt(sc.nextLine());

		switch (choixMenu) {

		case 1:
			gestionVolsMenu();
			break;

		case 2:
			gestionReservationMenu();
			break;

		case 3:
			System.out.println("Au revoir");
			continuer = false;
			break;

		default:
		}
	}

	public static void gestionVolsMenu() {

		System.out.println("--------------------------------");

		System.out.println("\t MENU GESTION DE VOL ");

		System.out.println("--------------------------------");

		System.out.println("1) Creation d'un vol");

		System.out.println("2) Liste des vols");

		System.out.println("3) Rechercher un avion par numéro");

		System.out.println("4) Rechercher un avion par ville de départ et d’arrivée : ");

		System.out.println("5) Quitter");

		choixMenu = Integer.parseInt(sc.nextLine());

		switch (choixMenu) {

		case 1:
			saveVol();
			break;

		case 2:
			displayAllVols();
			break;

		case 3:
			displayVolByNumber();
			break;

		case 4:
			displayVolByTowns();
			break;

		case 5:
			System.out.println("fin du programme");
			continuer = false;
			break;

		default:
		}
	}

	public static void gestionReservationMenu() {

		System.out.println("--------------------------------");

		System.out.println("\t MENU RESERVATION ");

		System.out.println("--------------------------------");

		System.out.println("1) Créer une réservation");

		System.out.println("2) Voir les réservations d’un vol");

		System.out.println("3) Annuler une réservation");

		System.out.println("4) Voir toutes les réservations d’une personne ");

		System.out.println("5) Quitter");

		choixMenu = Integer.parseInt(sc.nextLine());

		switch (choixMenu) {

		case 1:
			saveReservation();
			break;

		case 2:
			displayReservationByVol();
			break;

		case 3:
			removeReservation();
			break;

		case 4:
			displayReservationByName();
			break;

		case 5:
			System.out.println("fin du programme");
			break;

		default:
		}
	}

	public static void saveVol() {

		System.out.println("--- ENREGISTREMENT DE VOL ---");

		System.out.println("Entrer le numéro de vol ( format 0001): ");

		String NumeroVol = sc.nextLine();

		System.out.println("Entrer le type d'avion (A330, A340, A380, B747) : ");

		Avion typeAvion = Avion.valueOf(sc.nextLine());

		System.out.println("Entrer nb de place :");

		int nbPlace = Integer.valueOf(sc.nextLine());

		System.out.println("Entrer la ville de depart :");

		String villeDepart = sc.nextLine();

		System.out.println("Entrer la ville d'arriver :");

		String villeArrive = sc.nextLine();

		System.out.println("Entrer la date de depart au format dd-MM-yyyy :");

		String date = sc.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate dateArrive = LocalDate.parse(date, formatter);

		// LocalDateTime dateArrive = LocalDateTime.parse(date,
		// DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Vol v = new Vol(NumeroVol, typeAvion, nbPlace, villeDepart, villeArrive, dateArrive);

		dao.save(v);

		System.out.println("vol enregistré");

	}

	public static void displayAllVols() {

		List<Vol> vols = dao.findAllAvion();

		System.out.println("--------------------------------");
		System.out.println("\t LISTE DES VOLS ENREGISTREES");
		System.out.println("--------------------------------");

		for (Vol v : vols) {
			System.out.println(v.getNumeroVol() + "  |  " + v.getTypeAvion() + "  |  " + v.getVilleDeDepart() + "  |  "
					+ v.getVilleArrive() + "  |  " + v.getDateToDepart());

		}

	}

	public static void displayVolByNumber() {

		System.out.println("Veuiller rentrer le numéro de vol ( format 0001)");

		String NumeroVol = sc.nextLine();

		Vol v = dao.findVolByNumber(NumeroVol);

		System.out.println("--------------------------------");
		System.out.println("\t RESULTATS");
		System.out.println("--------------------------------");

		System.out.println(v.getNumeroVol() + "  |  " + v.getTypeAvion() + "  |  " + v.getVilleDeDepart() + "  |  "
				+ v.getVilleArrive() + "  |  " + v.getDateToDepart());

	}

	public static void displayVolByTowns() {

		System.out.println("Rentrer la ville de départ");

		String villeDepart = sc.nextLine();

		System.out.println("Rentrer la ville d'arrivée");

		String villeArrivee = sc.nextLine();

		List<Vol> vols = dao.findVolByTowns(villeDepart, villeArrivee);

		System.out.println("--------------------------------");
		System.out.println("\t RESULTATS");
		System.out.println("--------------------------------");

		for (Vol v : vols) {
			System.out.println(v.getNumeroVol() + "  |  " + v.getTypeAvion() + "  |  " + v.getVilleDeDepart() + "  |  "
					+ v.getVilleArrive() + "  |  " + v.getDateToDepart());

		}
	}

	public static void saveReservation() {

		String NumeroVol = "";

		System.out.println("--- RESERVATION ---");

		System.out.println("Veuiller rentrer le numéro de vol ( format 0001)");

		NumeroVol = sc.nextLine();

		Vol v = dao.findVolByNumber(NumeroVol);

		System.out.println("entrez votre nom");

		String n = sc.nextLine();

		System.out.println("entrez votre prenom");

		String p = sc.nextLine();

		System.out.println("entrez votre age");

		int a = Integer.parseInt(sc.nextLine());

		Reservation res = new Reservation(n, p, a, v);

		dao1.saveReservation(res);

		System.out.println("Reservation enregistré");

	}

	public static void displayReservationByVol() {

		String NumeroVol = "";

		System.out.println("Entrer le numéro de vol ( format 0001)");

		NumeroVol = sc.nextLine();

		List<Reservation> res = dao1.findReservationByVol(NumeroVol);

		System.out.println("--- RESERVATION ---");
		System.out.println("--------------------------------");

		for (Reservation r : res) {
			System.out.println("Num : " + r.getVolReserver() + "	| Nom: " + r.getNom() + " | Prénom : "
					+ r.getPrenom() + " | Age : " + r.getAge());

		}
	}

	public static void removeReservation() {

		System.out.println("Entrez le numéro de réservation à supprimer ");

		String n = sc.nextLine();

		dao1.removeReservationByNum(n);

		System.out.println("Reservation annulee");

	}

	public static void displayReservationByName() {

		System.out.println("Entrez votre nom : ");

		String n = sc.nextLine();

		List<Reservation> res = dao1.findReservationByName(n);

	}

}
