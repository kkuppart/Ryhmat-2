package rühmatöö2;
public class Isik {

	int xkoord;					//koordinaadid
	int ykoord;
	boolean on_nakatanud;
	boolean on_vaktsineeritud;
	int päevinakatumisest;
	InimeseKujutis kujutis;
	Populatsioon populatsioon;			//field populatsioon, mis antakse isikule kaasa

	public Isik(int xkoord, int ykoord, boolean on_nakatanud,
			Populatsioon populatsioon, boolean on_vaktsineeritud, int päevinakatumisest) {

		this.xkoord = xkoord;
		this.ykoord = ykoord;
		this.on_nakatanud = on_nakatanud;
		this.populatsioon = populatsioon;
		this.on_vaktsineeritud=on_vaktsineeritud;
		this.päevinakatumisest = päevinakatumisest;
	}

	public void tapa() {				//meetod nakatunud inimestest vabanemiseks
		populatsioon.inimesed[xkoord][ykoord] = null;
		populatsioon.inimeste_arv--;
	}
}
