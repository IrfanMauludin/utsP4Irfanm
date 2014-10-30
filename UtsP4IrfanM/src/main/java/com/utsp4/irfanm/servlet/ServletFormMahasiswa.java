package com.utsp4.irfanm.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utsp4.irfanm.dao.MahasiswaDao;
import com.utsp4.irfanm.database.KoneksiDatabase;
import com.utsp4.irfanm.model.Mahasiswa;

public class ServletFormMahasiswa extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String npm = request.getParameter("npm");
        String nama = request.getParameter("nama");
        String tmpt = request.getParameter("tmpt");
        String tgl = request.getParameter("tgl");
        String jk = request.getParameter("jk");
        String alamat = request.getParameter("alamat");
       // tglLahir = new SimpleDateFormat("dd/MM/yyyy").parse(tgl);

        try {
            //Date tglLahir = new SimpleDateFormat("dd/MM/yyyy").parse(tgl);
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/mahasiswa";
            String user = "root";
            String pass = "";

            KoneksiDatabase conn = new KoneksiDatabase(driver, url, user, pass);
            MahasiswaDao md = new MahasiswaDao(conn);
            Mahasiswa mhs = new Mahasiswa();

            mhs.setId(1);
            mhs.setNpm(npm);
            mhs.setNama(nama);
            mhs.setTempatLahir(tmpt);
            mhs.setTglLahir(tgl);
            mhs.setJenisKelamin(jk);
            mhs.setAlamat(alamat);
            md.simpan(mhs);
        } catch (Error ex) {
            Logger.getLogger(ServletFormMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("npm", npm);
        request.setAttribute("nama", nama);
        request.setAttribute("tmpt", tmpt);
        request.setAttribute("tgl", tgl);
        request.setAttribute("jk", jk);
        request.setAttribute("alamat", alamat);
        request.setAttribute("waktu", new Date());
        request.getRequestDispatcher("WEB-INF/templates/jsp/ViewMahasiswa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
