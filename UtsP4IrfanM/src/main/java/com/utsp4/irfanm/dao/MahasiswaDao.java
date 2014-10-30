package com.utsp4.irfanm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.utsp4.irfanm.database.KoneksiDatabase;
import com.utsp4.irfanm.model.Mahasiswa;

public class MahasiswaDao {
	private KoneksiDatabase koneksi;

	public MahasiswaDao(KoneksiDatabase k) {
		this.koneksi = k;
	}

	public void simpan(Mahasiswa k) {
		try {
			Connection c = koneksi.connect();
			long myId = 0;

			String sqlIdentifier = "select * from mahasiswa";
			PreparedStatement pst = c.prepareStatement(sqlIdentifier);
			synchronized (this) {
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					myId = rs.getLong(1);
				}
			}
			try {
				String sql = "insert into mahasiswa (npm, nama, tempatLahir, tanggalLahir, jenisKelamin, alamat) values (?,?,?,?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, k.getNpm());
				ps.setString(2, k.getNama());
				ps.setString(3, k.getTempatLahir());
				ps.setString(4, k.getTglLahir());
				ps.setString(5, k.getJenisKelamin());
				ps.setString(6, k.getAlamat());
				ps.executeUpdate();
				koneksi.disconnect(c);
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (SQLException ex) {
			Logger.getLogger(MahasiswaDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Mahasiswa> cari() throws Exception {
		List<Mahasiswa> hasil = new ArrayList<Mahasiswa>();

		try {
			String sql = "select * from mahasiswa order by npm";
			Connection c = koneksi.connect();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Mahasiswa k = konversiResultSet(rs);
				hasil.add(k);
			}
			koneksi.disconnect(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hasil;
	}

	private Mahasiswa konversiResultSet(ResultSet rs) throws Exception {
		Mahasiswa k = new Mahasiswa();
		k.setId(rs.getInt("ID"));
		k.setNpm(rs.getString("NPM"));
		k.setNama(rs.getString("NAMA"));
		k.setTempatLahir(rs.getString("TEMPATLAHIR"));
		k.setTglLahir(rs.getString("TANGGALLAHIR"));
		k.setJenisKelamin(rs.getString("JENISKELAMIN"));
		k.setAlamat(rs.getString("ALAMAT"));
		return k;
	}
}
