package odev;

import java.io.*;
import java.util.LinkedList;


public class Systemd  {

	public static final String ANSI_SIYAH = "\u001B[30m";
	public static final String ANSI_KIRMIZI = "\u001B[31m";
	public static final String ANSI_YESIL = "\u001B[32m";
	public static final String ANSI_SARI = "\u001B[33m";
	public static final String ANSI_MAVI = "\u001B[34m";
	public static final String ANSI_MOR = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_BEYAZ = "\u001B[37m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	public static void main(String[] args) throws IOException {
	
		
		
		int lines	= 0; //Program başlamadan önce kaç satır çalışacağını tespit etmek için satır sayısını sakladığımız değişken
		int i		= 0; //For döngülerinde kullanılmak üzere int değeri 
		int id		= 0; //Her prosesi kimliklendirmek amaçlı id değişkeni ile ayarlıyoruz
		String str	=""; //Her satırı sırayla aldığımız string değişkeni
		
		LinkedList<Proses> prosesler = new LinkedList<Proses>(); //Prosesler kuyruğu
		LinkedList<Proses> priority0 = new LinkedList<Proses>(); //RealTime kuyruk
		LinkedList<Proses> priority1 = new LinkedList<Proses>();
		LinkedList<Proses> priority2 = new LinkedList<Proses>();
		LinkedList<Proses> priority3 = new LinkedList<Proses>();
				
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.print("Proses için gerekli dosya tespit edilemedi");
        }//Komut penceresinden girdiğimiz dosya adı var mı diye kontrol fonksiyonu, eğer yoksa oluşturulur.

        FileReader fileReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fileReader);
       
        /*Bu try bloğu içerisinde linenumberreader çağırılıp giriş.txt dosyasındaki satır sayısını elde ediyoruz*/
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) ;

            lines = lnr.getLineNumber();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 3 priority kuyruğu mu yoksa n mi
        // proses kuyruklarınıthread içinde hepsini yannada çalıştırsak olmaz mı,, yoksa thread kullanmadan mı
        
        /*Üstteki try bloğundan elde ettiğimiz satır sayısını kullanarak her satır için geliş süresi, öncelik ve çalışma süresi
         * tespit edilip gerekli eşitlikler sağlanıp işlenmek üzere kuyruklara aktarılacak*/
        
        for(;lines > 0; lines--) {
        	
        	str = bReader.readLine(); 					// Satır komple okunup str string değişkenine atanır

    		/*TÜM SATIRLARDAKİ SAYILARI ALIYORUZ, ŞİMDİ BUNLARLA PROSES OLUŞTURMAMIZ LAZIM*/
        	
    		prosesler.add(new Proses(++id,														/*id */ 			
    				Integer.parseInt(str.substring(0, str.indexOf(','))),						/*Geliş Zamanı*/
    				Integer.parseInt(str.substring(str.indexOf(',')+2, str.lastIndexOf(','))),	/*Öncelik*/
    				Integer.parseInt(str.substring(str.lastIndexOf(',')+2,str.length())))); 	/*Çalışma Süresi*/
    		//Proses listesine prosesler oluşturuluyor
        }
        
        bReader.close();//Okuyucuyla işimiz bitti ve kapattık
        
        
        /*Burdan aşağısı tüm prosesler oluşturulup listeye konduktan sonrası*/
        
        for(i = 0; i<prosesler.size(); i++) {
        	System.out.println(ANSI_BEYAZ +"Proses ID: " 			+ prosesler.get(i).GetProsesID() + ANSI_RESET);
        	System.out.println(ANSI_SARI + "Proses Varış Zamanı: " 	+ prosesler.get(i).GetVarisZamani() + ANSI_RESET);
        	System.out.println(ANSI_YESIL + "Proses Önceliği: " 		+ prosesler.get(i).GetOncelik() + ANSI_RESET);
        	System.out.println(ANSI_CYAN + "Proses Çalışma Süresi: "+ prosesler.get(i).GetProsesZamani() + ANSI_RESET);
        	System.out.println("\n--------\n");
        	
        	if(prosesler.get(i).GetOncelik() == 2) {
        		priority2.add(prosesler.pop());
        	}
        }
        
        for(i = 0; i < priority2.size(); i++) {
        	System.out.print("p2 ye alındı"); // çalışmadı la
        	System.out.println(priority2.get(i).GetProsesID());
        }
	
	}
	
	/*
	 * oluşturulduğunda
	 * askıya alındığına
	 * sonlandığında mesaj verecek
	 * */
	
	

}
