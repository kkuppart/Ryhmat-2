package rühmatöö2;

public class Populatsioon {
	
	int ulatus; // näitab, kui suur on ruudu pikkus, milles populatsioon asetseb
	double tihedus; // näitab, kui suure tõenäosusega luuakse mingisse
	// konkreetsesse ruudustikku ruutu inimene
	double vaktsineeritud;
	Isik[][] inimesed; // loob objektidest Isik kahemõõtmelise järjendi
	
	int inimeste_arv;
	
	
	public Populatsioon(final int ulatus, final double tihedus, final double vaktsineeritud) {
		
		inimesed = new Isik[ulatus][ulatus];
		this.ulatus = ulatus;
		this.tihedus = tihedus;
		for ( int i = 0; i < ulatus; i++ ) {
			
			for ( int j = 0; j < ulatus; j++ ) {
				
				if ( tihedus > Math.random() ) {
					final boolean onvaktsineeritud = vaktsineeritud > Math.random();
					int päevinakatumisest =0;
					inimesed[i][j] = new Isik(i, j, false, this, onvaktsineeritud,päevinakatumisest);
					inimeste_arv++;
				}
				
			}
		}

	}
}