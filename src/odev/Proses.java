package odev;

public class Proses {
	
	private int prosesID;
	private int varisZamani;
	private int oncelik;
	private int prosesZamani;
	
	public Proses(int prosesID, int varisZamani, int oncelik, int prosesZamani) {
		
		this.prosesID = prosesID;
		this.varisZamani = varisZamani;
		this.oncelik = oncelik;
		this.prosesZamani = prosesZamani;
		
		System.out.println("\u001B[33m" + "Proses " + prosesID + " oluşturuldu!\n");
		
	}

	
	public int GetProsesID() {
		return prosesID;
	}
	
	public int GetVarisZamani() {
		return varisZamani;
	}
	
	public int GetOncelik(){
		return oncelik;
	}
	
	public int GetProsesZamani() {
		return prosesZamani;
	}
	
}
