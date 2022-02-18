import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public void sortKaryawan(ArrayList<Karyawan> listKar)
	{
		//sort
		Collections.sort(listKar,new Comparator<Karyawan>() {
			@Override
			public int compare(Karyawan a, Karyawan b) {
				return a.getNama().compareTo(b.getNama());
			}
		});
		
		//update index
		int idx = 1;
		for (Karyawan finder : listKar) {
			finder.setIndex(idx);
			idx++;
		}
	}
	
	public int findJabatan(String jabatan) {
		if (jabatan.equals("Manager")) {
			return 1;

		} else if (jabatan.equals("Supervisor")) {
			return 2;

		} else if (jabatan.equals("Admin")) {
			return 3;
		}
		return 0;

	}

	public int setGaji(String jabatan) {
		if (jabatan.equals("Manager")) {
			return 8000000;

		} else if (jabatan.equals("Supervisor")) {
			return 6000000;

		} else if (jabatan.equals("Admin")) {
			return 4000000;
		}
		return 0;

	}

	public void viewer(ArrayList<Karyawan> listKar) {
		// console format
		String form1 = "%-3s";
		String form2 = "%-18.18s";
		String form3 = "%-35.35s";
		String format = "| " + form1 + "| " + form2 + "| " + form3 + "| " + form2 + "| " + form2 + "| " + form2 + "|\n";
		String format2 = "|" + form1 + "|" + form2 + "-|-" + form3 + "|" + form2 + "-|" + form2 + "-|" + form2 + "-|\n";

		// print
		int idx = 1;

		System.out.println();
		System.out.format(format2, "----", "-----------------------", "----------------------------------",
				"-------------------", "-------------------", "--------------------");
		System.out.format(format, "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.format(format2, "----", "-----------------------", "----------------------------------",
				"-------------------", "-------------------", "-------------------");

		for (Karyawan viewKar : listKar) {

			System.out.format(format, idx, viewKar.getKode(), viewKar.getNama(), viewKar.getKelamin(),
					viewKar.getJabatan(), viewKar.getGaji());
			idx++;
		}

		System.out.format(format2, "----", "-----------------------", "----------------------------------",
				"-------------------", "-------------------", "-------------------");

	}

	public Main() {

		Random rand = new Random();
		ArrayList<Karyawan> listKar = new ArrayList<Karyawan>();
		Scanner scan = new Scanner(System.in);

		int option = 0;
		int gaji = 0;
		String nama, kelamin, jabatan, kode;
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Karyawan kar;
		int idx = 1;

		// initialize ctr bonus
		int mCtr = 0;
		int sCtr = 0;
		int aCtr = 0;
		Boolean mFlag, sFlag, aFlag;

		// init update
		int nUpdate;

		do {
			System.out.println();
			System.out.println("=======================");
			System.out.println("         MENU");
			System.out.println("=======================");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("5. Exit");
			System.out.print("Choose menu option: ");

			option = scan.nextInt();
			scan.nextLine();

			switch (option) {
			case 1:
				// nama karyawan
				do {
					System.out.print("Input nama karyawan [>= 3]: ");
					nama = scan.nextLine();
				} while (nama.length() < 3);

				// jenis kelamin
				do {
					System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
					kelamin = scan.nextLine();
				} while (!(kelamin.equals("Laki-laki") || kelamin.equals("Perempuan")));

				// jabatan
				do {
					System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
					jabatan = scan.nextLine();
				} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));

				// generate random kode
				kode = "" + alph.charAt(rand.nextInt(alph.length())) + alph.charAt(rand.nextInt(alph.length())) + "-"
						+ Math.abs(rand.nextInt() % 10) + Math.abs(rand.nextInt() % 10) + Math.abs(rand.nextInt() % 10)
						+ Math.abs(rand.nextInt() % 10);

				// set gaji

				mFlag = false;
				sFlag = false;
				aFlag = false;

				gaji = setGaji(jabatan);

				// set bonus

				if (jabatan.equals("Manager")) {

					mCtr++;

					if (mCtr % 3 == 1 && mCtr != 1) {
						mFlag = true;
						for (Karyawan bonus : listKar) {
							String stemp = bonus.getJabatan();

							if (stemp.equals("Manager")) {
								int temp = bonus.getGaji();
								int calc = temp + ((temp * 10) / 100);
								bonus.setGaji(calc);
							}
						}
					}

				} else if (jabatan.equals("Supervisor")) {

					sCtr++;

					if (sCtr % 3 == 1 && sCtr != 1) {
						sFlag = true;
						for (Karyawan bonus : listKar) {
							String stemp = bonus.getJabatan();

							if (stemp.equals("Supervisor")) {
								int temp = bonus.getGaji();
								int calc = temp + ((temp * 75) / 1000);
								bonus.setGaji(calc);
							}
						}
					}

				} else if (jabatan.equals("Admin")) {

					aCtr++;

					if (aCtr % 3 == 1 && aCtr != 1) {
						aFlag = true;
						for (Karyawan bonus : listKar) {
							String stemp = bonus.getJabatan();

							if (stemp.equals("Admin")) {
								int temp = bonus.getGaji();
								int calc = temp + ((temp * 5) / 100);
								bonus.setGaji(calc);
							}
						}
					}
				}

				// tampung data
				kar = new Karyawan(nama, kelamin, jabatan, kode, gaji, idx);
				listKar.add(kar);

				// print feedback
				System.out.println("Berhasil menambahkan karyawan dengan id " + kode);

				
				int ctr = 0;

				if (mFlag == true) {
					System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
					for (Karyawan bonus : listKar) {
						String stemp = bonus.getJabatan();
						String stemp2 = bonus.getKode();
						if (stemp.equals("Manager") && !(stemp2.equals(kode))) {
							if (ctr == 0) {
								System.out.print(stemp2);
								ctr++;
							} else {
								System.out.print(", " + stemp2);
							}
						}
					}

				} else if (sFlag == true) {
					System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
					for (Karyawan bonus : listKar) {
						String stemp = bonus.getJabatan();
						String stemp2 = bonus.getKode();
						if (stemp.equals("Supervisor") && !(stemp2.equals(kode))) {
							if (ctr == 0) {
								System.out.print(stemp2);
								ctr++;
							} else {
								System.out.print(", " + stemp2);
							}
						}
					}
				} else if (aFlag == true) {
					System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
					for (Karyawan bonus : listKar) {
						String stemp = bonus.getJabatan();
						String stemp2 = bonus.getKode();
						if (stemp.equals("Admin") && !(stemp2.equals(kode))) {
							if (ctr == 0) {
								System.out.print(stemp2);
								ctr++;
							} else {
								System.out.print(", " + stemp2);
							}
						}
					}
				}
				System.out.print("\n");
				System.out.println("ENTER to return");
				idx++;

				break;
			case 2:
				//sort
				sortKaryawan(listKar);
				
				// view
				viewer(listKar);
				
				

				break;
			case 3:
				//sort
				sortKaryawan(listKar);
				
				// update
				viewer(listKar);

				// input index
				System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
				nUpdate = scan.nextInt();
				scan.nextLine();

				// input nama
				do {
					System.out.print("Input nama karyawan [>= 3]: ");
					nama = scan.nextLine();
					
				} while (!(nama.length() >= 3 || nama.equals("0")));

				// jenis kelamin
				do {
					System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
					kelamin = scan.nextLine();
				} while (!(kelamin.equals("Laki-laki") || kelamin.equals("Perempuan") || kelamin.equals("0")));

				// jabatan
				do {
					System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
					jabatan = scan.nextLine();
				} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")
						|| jabatan.equals("0")));

				// update values
				for (Karyawan finder : listKar) {
					if (finder.getIndex() == nUpdate) {
						if (nama.equals("0")) {
							nama = finder.getNama();
						}
						if (kelamin.equals("0")) {
							kelamin = finder.getKelamin();
						}
						if (jabatan.equals("0")) {
							jabatan = finder.getJabatan();
							gaji = finder.getGaji();
						}else if (!(jabatan.equals("0"))) {
							gaji = setGaji(jabatan);
							int temp = findJabatan(finder.getJabatan());
							if (temp==1) {
								mCtr--;
							}else if (temp==2) {
								sCtr--;
							}else if (temp==3) {
								aCtr--;
							}
							
							int temp2 = findJabatan(jabatan);
							if (temp2==1) {
								mCtr++;
							}else if (temp2==2) {
								sCtr++;
							}else if (temp2==3) {
								aCtr++;
							}
						}
						
						kode = finder.getKode();
						idx = finder.getIndex();

						finder.setNama(nama);
						finder.setGaji(gaji);
						finder.setIndex(idx);
						finder.setJabatan(jabatan);
						finder.setKode(kode);
						finder.setKelamin(kelamin);

						System.out.println("Berhasil mengupdate karyawan dengan id " + kode);
						System.out.println("ENTER to return");
					}
				}

				break;
			case 4:
				//sort
				sortKaryawan(listKar);
				// delete
				viewer(listKar);

				// input index
				int nDelete;
				System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
				nDelete = scan.nextInt();
				scan.nextLine();

				Iterator<Karyawan> itr = listKar.iterator();

				// delete data
				while (itr.hasNext()) {
					Karyawan finder = (Karyawan) itr.next();
					if (finder.getIndex() == nDelete) {
						int temp = findJabatan(finder.getJabatan());
						if (temp==1) {
							mCtr--;
						}else if (temp==2) {
							sCtr--;
						}else if (temp==3) {
							aCtr--;
						}
						itr.remove();
						System.out.println("Karyawan dengan kode " + finder.getKode() + " berhasil dihapus");
						System.out.println("ENTER to return");
					}
				}

				// update index
				idx = 1;
				for (Karyawan finder : listKar) {
					finder.setIndex(idx);
					idx++;
				}

				break;

			default:

				break;
			}
		} while (option > 0 && option <= 4);
	}

	public static void main(String[] args) {
		new Main();

	}

}
