create table mahasiswa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    npm VARCHAR(16),
    nama VARCHAR(255),
    tempatLahir VARCHAR(255),
    tanggalLahir VARCHAR(10),
    jenisKelamin VARCHAR(12),
    alamat VARCHAR(255),
    no_hp VARCHAR(20)
) Engine=InnoDB;