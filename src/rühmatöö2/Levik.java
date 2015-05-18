package rühmatöö2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Levik extends Application {

	static private Populatsioon pop;
	static private int inimesteArvAlguses;
	static private Viirus viirus;
	static PrintWriter logiKirjutaja;

	static private Stage peaLava;

	private static void initSimulation() {
		System.out
				.println("tere! See on lihtne programm viiruste leviku simuleerimiseks."
						+ " Populatsiooni kujutatakse ette ruuduna, millel asetsevad mingi"
						+ "\n"
						+ "tihedusega "
						+ "inimesed."
						+ "\n"
						+ "Alguses luuakse populatsioon ning mingi hulk viirusega nakatunud inimesi "
						+ "kellelt see hakkab mingi tõenäosusega nendega"
						+ "\n"
						+ "kokkupuutuvatele"
						+ " inimestele levima. Viirus tapab kõik nakatunud, aga on võimalik jälgida, "
						+ "kuidas inimeste tihedus ja viiruse nakatuvus"
						+ "\n"
						+ "mõjutab suremust. ");
		System.out
				.println("----------------------------------------------------------------------------------------------------------------------");

		System.out
				.println("Loome uue populatsiooni. Mis peaks olema populatsiooni ulatus?(täisarv)"
						+ " Ulatus^2 on inimeste arv juhul, kui inimeste tihedus = 1.");

		TextInputDialog dialog = new TextInputDialog("sisesta");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Mis peaks olema populatsiooni ulatus?(täisarv) Ulatus^2 on inimeste arv juhul, kui inimeste tihedus = 1");
		dialog.setContentText("ulatus:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			int ulatus = Integer.valueOf(result.get());

			TextInputDialog dialog2 = new TextInputDialog("sisesta");
			dialog2.setTitle("pealkiri");
			dialog2.setHeaderText("Mis peaks olema inimeste paiknemise tihedus? (0.0-1.0) Kui inimesed paikevad hõredamalt, levivad viirused kehvemini");
			dialog2.setContentText("Inimeste tihedus:");
			Optional<String> result2 = dialog2.showAndWait();
			if (result.isPresent()) {
				double tihedus = Double.valueOf(result2.get());

				TextInputDialog dialog3 = new TextInputDialog("efefe");
				dialog3.setTitle("värksärk");
				dialog3.setHeaderText("fefe");
				dialog3.setContentText("Vaktsineeritute osakaal:");
				Optional<String> result3 = dialog3.showAndWait();
				if (result.isPresent()) {
					double vaktsineeritud = Double.valueOf(result3.get());

					System.out
							.println("mis peaks olema inimeste paiknemise tihedus?(ujukomaarv 0 ja 1.0 vahel)");

					pop = new Populatsioon(ulatus, tihedus, vaktsineeritud); // Loome
																				// uue
					// populatsiooni pop
					dialog.close();
					System.out.println("inimeste arv on " + pop.inimeste_arv
							+ "\n");
					inimesteArvAlguses = pop.inimeste_arv;

					System.out
							.println("mis peaks olema viirusega nakatumise tõenäosus kokkupuutel?(ujukomaarv 0 ja 1.0 vahel)"
									+ "\n");

					double nakatuvus = 1.0;
					viirus = new Viirus(1.0, nakatuvus); // loome uue viiruse

					System.out.println("inimeste arv alguses on "
							+ pop.inimeste_arv);

				}

				int nakatunute_arv;
				// if ( inimesteArvAlguses / 10 > 1 ) {
				// nakatunute_arv = inimesteArvAlguses / 10;
				// }
				//
				nakatunute_arv = 3; // võtame alguses mingi arvu nakatunuid

				ArrayList<Isik> algnakatunud = new ArrayList<>();
				for (int i = 0; i < nakatunute_arv; i++) { // ja märgime
															// suvalises kohas
															// asetsevate
															// inimeste välja
															// on_nakatunud
															// Tõeseks.
					final int a = (int) Math.floor(Math.random() * pop.ulatus);
					final int b = (int) Math.floor(Math.random() * pop.ulatus);
					if (pop.inimesed[a][b] != null) {
						pop.inimesed[a][b].on_nakatanud = true;
						algnakatunud.add(pop.inimesed[a][b]);
					}
				}

			}
		}
	}

	@Override
	public void start(Stage peaLava) throws Exception {
		initSimulation();
		int scenesize = 1000;
		final Group juur = new Group(); // luuakse juur

		for (int i = 0; i < pop.ulatus; i++) {
			for (int j = 0; j < pop.ulatus; j++) {
				if (pop.inimesed[i][j] == null) {
					continue;
				}

				InimeseKujutis ring = new InimeseKujutis(100
						+ (scenesize - 400) / (pop.ulatus) * i, 100
						+ (scenesize - 400) / (pop.ulatus) * j,
						(scenesize - 400) / (2 * pop.ulatus), i, j);
				pop.inimesed[i][j].kujutis = ring;
				if (pop.inimesed[i][j].on_nakatanud) {
					ring.setFill(Color.RED);
				} else {
					ring.setFill(Color.GREEN);

				}
				if (pop.inimesed[i][j].on_vaktsineeritud) {
					ring.setFill(Color.GRAY);
				}

				juur.getChildren().add(ring); // ristkülik lisatakse juure
												// alluvaks
			}

		}

		final Scene stseen1 = new Scene(juur, scenesize, scenesize, Color.SNOW); // luuakse
																					// stseen

		peaLava.setTitle("Must ruut"); // lava tiitelribale pannakse tekst

		peaLava.setScene(stseen1); // lavale lisatakse stseen

		peaLava.show(); // lava tehakse nähtavaks
		Levik.peaLava = peaLava;

		startSimulation();
	}

	private static Animation buildAnimation(
			ArrayList<ArrayList<Integer>> uuedNakatunud) {
		List<Transition> transitions = new ArrayList<Transition>();
		for (ArrayList<Integer> koordinaadid : uuedNakatunud) {
			Isik isik = pop.inimesed[koordinaadid.get(0)][koordinaadid.get(1)];
			if (isik == null) {
				System.out.println("PAHA");
				continue;
			}
			Transition transition = new FillTransition(
					Duration.millis(30000 / pop.ulatus), isik.kujutis,
					Color.RED, Color.BLACK);
			transition.setCycleCount(0);
			transitions.add(transition);
		}
		return new ParallelTransition(transitions.toArray(new Transition[0]));
	}

	private static int k;
	private static int s;

	private static void startSimulation() {
		s = 0;
		k = 0;

		runSimulation();
	}

	private static void runSimulation() {
		s = pop.inimeste_arv;

		final ArrayList<ArrayList<Integer>> nakatunud = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < pop.ulatus; i++) { // i ja j on rea - ja
												// veeruindeksid

			for (int j = 0; j < pop.ulatus; j++) {

				if (pop.inimesed[i][j] != null
						&& pop.inimesed[i][j].on_nakatanud == true) {
					if (Levik.k == 0) {
						final ArrayList<Integer> algkoords = new ArrayList<Integer>();
						algkoords.add(pop.inimesed[i][j].xkoord);
						algkoords.add(pop.inimesed[i][j].ykoord);
						nakatunud.add(algkoords);

					}

					if (i + 1 < pop.ulatus // proovime nakatada asukohal i+1, j asetsevat inimest
							&& pop.inimesed[i + 1][j] != null
							&& pop.inimesed[i + 1][j].on_vaktsineeritud == false) {
						if (viirus.nakatumus > Math.random()) {
							final ArrayList<Integer> koords = new ArrayList<Integer>();
							koords.add(pop.inimesed[i + 1][j].xkoord);
							koords.add(pop.inimesed[i + 1][j].ykoord);
							nakatunud.add(koords); // lisame nakatunud
							// inimese koordinaadid
							// Arraylisti, et hiljem
							// muuta nende omadus
							// on_nakatunud true-ks.
						}
					}

					if (j + 1 < pop.ulatus // teeme sama kõikide nakatunud
							// inimese lähedal olevate(mitte
							// diagonaalis paiknevate)
							// inimestega.
							&& pop.inimesed[i][j + 1] != null
							&& pop.inimesed[i][j + 1].on_vaktsineeritud == false) {
						if (viirus.nakatumus > Math.random()) {
							final ArrayList<Integer> koords = new ArrayList<Integer>();
							koords.add(pop.inimesed[i][j + 1].xkoord);
							koords.add(pop.inimesed[i][j + 1].ykoord);
							nakatunud.add(koords);
						}
					}

					if (i > 0
							&& pop.inimesed[i - 1][j] != null
							&& pop.inimesed[i - 1][j].on_vaktsineeritud == false) {
						if (viirus.nakatumus > Math.random()) {
							final ArrayList<Integer> koords = new ArrayList<Integer>();
							koords.add(pop.inimesed[i - 1][j].xkoord);
							koords.add(pop.inimesed[i - 1][j].ykoord);
							nakatunud.add(koords);
						}
					}
					Isik isik4 = pop.inimesed[i][j - 1];
					if (j > 0 && isik4 != null
							&& isik4.on_vaktsineeritud == false
							&& isik4.on_nakatanud == false) {
						if (viirus.nakatumus > Math.random()) {
							final ArrayList<Integer> koords = new ArrayList<Integer>();
							koords.add(isik4.xkoord);
							koords.add(isik4.ykoord);
							nakatunud.add(koords);
						}
					}

				}
			}

		}

		for (int i = 0; i < pop.ulatus; i++) {
			for (int j = 0; j < pop.ulatus; j++) {
				if (pop.inimesed[i][j] != null
						&& pop.inimesed[i][j].on_nakatanud == true) {
					pop.inimesed[i][j].päevinakatumisest++;
					pop.inimesed[i][j].tapa();

				}
			}
		}

		for (int i = 0; i < nakatunud.size(); i++) {
			if (pop.inimesed[nakatunud.get(i).get(0)][nakatunud.get(i).get(1)] != null) {
				pop.inimesed[nakatunud.get(i).get(0)][nakatunud.get(i).get(1)].on_nakatanud = true;
			}
		}

		logiKirjutaja.print(k);
		logiKirjutaja.print(" ");
		logiKirjutaja.print("surnud on");
		logiKirjutaja.print(" ");
		logiKirjutaja.println(inimesteArvAlguses - pop.inimeste_arv);
		logiKirjutaja.println("inimest");

		k++;

		Animation animation = buildAnimation(nakatunud);
		animation.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (s != pop.inimeste_arv) {
					runSimulation();
				} else {
					endSimulation();
				}
			}
		});
		animation.play();
	}

	private static void endSimulation() {
		Dialog<ButtonType> dialog = new Alert(AlertType.CONFIRMATION);
		StringBuilder sb = new StringBuilder();
		sb.append("Inimeste arv simulatsiooni lõpus on " + pop.inimeste_arv
				+ ".\n");
		sb.append("Suremus on "
				+ ((float) inimesteArvAlguses - pop.inimeste_arv) * 100
				/ inimesteArvAlguses + "%.\n");
		sb.append("Kas soovid simulatsiooni korrata?");
		String dialogText = sb.toString();

		dialog.setTitle("Kas soovid simulatsiooni korrata?");
		dialog.setHeaderText(dialogText);

		Optional<ButtonType> result = dialog.showAndWait();
		// result.isPresent() &&
		if (result.get().equals(ButtonType.OK)) {

			// System.out.println("Resettime");
			reset();

			startSimulation();
		} else {
			Platform.exit();
		}
	}

	private static void reset() {
		initSimulation();
		Group juur = (Group) peaLava.getScene().getRoot();
		juur.getChildren().clear();
		for (int i = 0; i < pop.ulatus; i++) {
			for (int j = 0; j < pop.ulatus; j++) {
				if (pop.inimesed[i][j] == null) {
					continue;
				}

				InimeseKujutis ring = new InimeseKujutis(100 + 10 * i,
						100 + 10 * j, 5, i, j);
				pop.inimesed[i][j].kujutis = ring;

				ring.setFill(Color.GREEN);
				juur.getChildren().add(ring); // ristkülik lisatakse juure
												// alluvaks
			}

		}
	}

	public static void main(final String[] argv) {

		// final String pwd = System.getProperty("user.dir");
		// System.out.println(pwd);
		// final File file = new File(pwd + "/logi.txt");
		try {
			logiKirjutaja = new PrintWriter("logi.txt");
		} catch (FileNotFoundException e) {
			System.err
					.println("Logi faili ei õnnestunud avada. Now, this world will suffer. Good bye...");
		}
		logiKirjutaja.println("tere");

		launch(argv);

		logiKirjutaja.close();
	}

}
