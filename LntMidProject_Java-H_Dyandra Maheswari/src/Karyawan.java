

public class Karyawan {
	
	private String nama;
	private String kelamin;
	private String jabatan;
	private String kode;
	private int gaji;
	private int index;

	public Karyawan(String nama, String kelamin, String jabatan, String kode, int gaji, int index) {
		super();
		this.nama = nama;
		this.kelamin = kelamin;
		this.jabatan = jabatan;
		this.kode = kode;
		this.gaji = gaji;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getGaji() {
		return gaji;
	}

	public void setGaji(int gaji) {
		this.gaji = gaji;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKelamin() {
		return kelamin;
	}

	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	
	
	

}

