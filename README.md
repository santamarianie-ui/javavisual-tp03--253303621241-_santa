# Character Manager — TP3 (Java Visual SIH-6406)

## Deskripsi Project

Character Manager merupakan program sederhana berbasis Java yang digunakan untuk mengelola data karakter dengan menerapkan konsep Object-Oriented Programming (OOP). Program ini memiliki fitur untuk membuat karakter, menampilkan informasi karakter, menaikkan level, serta mengelola mana dan memeriksa status kehidupan karakter menggunakan method `isAlive()`.

Project ini dibuat sebagai implementasi pembelajaran Java mengenai class, object, constructor, encapsulation, enum, getter, setter, dan method.

## Screenshot Output Terminal

Berikut merupakan hasil pengujian program Character Manager melalui terminal Visual Studio Code.

![Screenshot Output Terminal](output-terminalTP3.png)

Output program mencakup:
- Menampilkan 3 karakter: Arjuna, Merlin, dan Athena.
- Melakukan proses level up pada karakter Arjuna.
- Menampilkan perubahan level, HP, dan attack power.
- Membuat karakter baru bernama Luna.
- Mengatur mana Luna menjadi 100.
- Menguji status kehidupan karakter menggunakan method `isAlive()`.

## Struktur Folder Project

```text
cadangan character/
│
├── src/
│   ├── com/
│   │   └── unpri/
│   │       └── charactermanager/
│   │           ├── Main.java
│   │           │
│   │           └── model/
│   │               ├── Character.java
│   │               └── CharacterType.java
│
└── README.md
```

## Identitas Mahasiswa

- **Nama:** Santa Mariani Elisabeth Simangunsong
- **NIM:** [253303621241]
- **Program Studi:** Sistem Informasi
- **Universitas:** Universitas Prima Indonesia

## Link Google Docs — Bagian D

[https://1drv.ms/b/c/251df1b2271152b7/IQBqCa0O3Q1wQbgZeQnYluOWARnYU1lLalO2W2HIiKMZ334?e=MIEOYt]

---

**Mata Kuliah:** Java Visual SIH-6406  
**Tugas:** Character Manager Sprint #1  
**Bahasa Pemrograman:** Java  
**JDK:** 17 atau lebih baru

# Character Manager - TP5 (Java Visual SIH-6406)

## Deskripsi Project
Character Manager menggunakan bahasa Java dengan menerapkan konsep Object-Oriented Programming (OOP), khususnya pengelolaan objek, reference, dan encapsulation. Pada tugas ini, mahasiswa diminta mengembangkan class CharacterManager agar mampu mengelola data karakter melalui fitur CRUD (Create, Read, Update, Delete), menambahkan fitur analitik, serta memahami bagaimana reference bekerja dalam Java.

## Screenshot Output Terminal

Berikut merupakan hasil pengujian program Character Manager melalui terminal Visual Studio Code.

![Screenshot Output Terminal](output-terminalTP05)

Output program mencakup:
 Menambahkan karakter (Arjuna, Merlin, Athena).
 Menguji perbandingan karakter menggunakan equals().
 Menguji penolakan nama duplikat.
 Menampilkan seluruh data karakter.
 Menguji pencarian dan penghapusan karakter.
 Menghitung total dan rata-rata level.
 Menguji findByType().
 Menguji salinan daftar (getAllCharacters()).
 Menguji fitur analitik countByType().
 Membuktikan konsep reference menggunakan dua variabel objek.

 # Struktur Folder Project

cadangan character/
│
├── src/
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               │
│               ├── Main.java
│               │
│               └── model/
│                   ├── Character.java
│                   ├── CharacterManager.java
│                   └── CharacterType.java
│
├── output-terminalTP3.png
└── README.md

# Fitur Analitik Rancangan Sendiri

Pada project ini, saya mengembangkan fitur analitik tambahan berupa method countByType() pada class CharacterManager.

Method countByType() berfungsi untuk menghitung jumlah karakter berdasarkan jenis atau tipe karakter tertentu, seperti WARRIOR, MAGE, ARCHER, dan HEALER.

Cara Kerja Method

Method ini menerima parameter berupa CharacterType, kemudian melakukan perulangan terhadap daftar karakter untuk memeriksa tipe masing-masing karakter. Apabila tipe karakter sesuai dengan parameter yang diberikan, jumlah penghitung akan bertambah satu.

# Penerapan Encapsulation

Method ini tetap menjaga prinsip encapsulation karena tidak memberikan akses langsung kepada pengguna terhadap ArrayList internal. Pengguna hanya menerima hasil perhitungan berupa nilai integer sehingga data asli tetap terlindungi.

Selain itu, method dilengkapi validasi untuk memastikan parameter tipe karakter tidak bernilai null dan dokumentasi Javadoc untuk menjelaskan fungsi method.

Alasan Pemilihan Method

Saya memilih countByType() karena fitur ini dapat mempermudah proses pengelompokan dan perhitungan jumlah karakter berdasarkan jenisnya. Dengan adanya fitur ini, pengguna tidak perlu menghitung jumlah karakter secara manual.

Fitur ini juga membuat pengelolaan data menjadi lebih praktis, efisien, dan mudah dikembangkan apabila terdapat penambahan jenis karakter baru.

Identitas Mahasiswa

Nama: Santa Mariani Elisabeth Simangunsong
NIM: 253303621241
Program Studi: Sistem Informasi
Fakultas: Sains dan Teknologi
Universitas: Universitas Prima Indonesia

# Link Google Docs - Bagian D

[https://1drv.ms/b/c/251df1b2271152b7/IQDEDFv0eVXHSIcTdV_Gs-VhAZ9Z8qvlclvV6vV4gejI-7g?e=AKL0IF]

Mata Kuliah: Java Visual SIH-6406
Project: Character Manager Sprint #1
Bahasa Pemrograman: Java
JDK: 17

# Character Manager Sprint #3

# Deskripsi Projek
TP8 (Tugas Praktikum 8 — Character Manager Sprint #3) adalah pertemuan penting yang menandai transisi besar dari program berbasis teks (console) ke antarmuka grafis (GUI) menggunakan Java Swing.

## Screenshot Output Terminal
**Kondisi Awal (5 karakter):
![Screenshoot Awal] (output-terminal sebelumTP08)
kondisi setelah perubahan (8 karakter)
![Screenshoot Setelah perubahan] (output-terminal sesudahTP08)

# Aliran Data & Konsep MVC
Aliran data dimulai dari kelas Main (berperan sebagai Controller inisialisasi) yang membuat objek karakter lalu memasukkannya ke dalam CharacterManager (berperan sebagai Model untuk menyimpan daftar karakter dan menghitung statistik rata-rata level). Objek CharacterManager tersebut kemudian diteruskan ke MainWindow (berperan sebagai View berbasis Java Swing) untuk merender daftar karakter beserta nilai rata-ratanya secara dinamis ke antarmuka pengguna. Pemisahan tanggung jawab ini menerapkan pola arsitektur MVC (Model-View-Controller), di mana logika pengelolaan data di dalam Model terisolasi sepenuhnya dari tampilan antarmuka pada View sehingga kode lebih modular dan mudah dipelihara.

#  Daftar Karakter Baru & Alasan Pemilihan Tipe/Level
Nama KarakterTipe (CharacterType)LevelAlasan Pemilihan Tipe & LevelRaka
- MAGE 5 Menyeimbangkan komposisi serangan jarak jauh berbasis sihir dalam tim, dengan Level 5 sebagai level menengah yang kompetitif.
- FreyaHEALER6Memperkuat daya tahan (support) tim karena sebelumnya baru terdapat satu 
- Healer (Luna), dengan Level 6 untuk kapasitas pemulihan lebih tinggi.
- OrionARCHER 6Menambah variasi penyerang fisik jarak jauh mendampingi Athena, dengan Level 6 guna meningkatkan rata-rata kekuatan keseluruhan tim.  
 
# Link Google Docs - Bagian D
[https://1drv.ms/b/c/251df1b2271152b7/IQDbopTQA6o0TomgWWKhvZvJAc8tDxn3JBCgbx3WMEovtIM?e=fhNfYt]