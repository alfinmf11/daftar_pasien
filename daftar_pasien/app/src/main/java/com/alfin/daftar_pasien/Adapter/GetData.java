package com.alfin.daftar_pasien.Adapter;

public class GetData {

    //    String inisial="",
    String nama="", jk="", alamat="", keluhan="", nomor_handphone="", id_pasien;

    public GetData(
//            String inisial,
            String id_pasien, String nama, String jk, String alamat, String keluhan, String nomor_handphone){
//        this.inisial=inisial;
        this.id_pasien=id_pasien;
        this.nama=nama;
        this.jk=jk;
        this.alamat=alamat;
        this.keluhan=keluhan;
        this.nomor_handphone=nomor_handphone;
    }
    public String getId_pasien() {
        return id_pasien;
    }
    public String getNama() { return nama; }
    public String getJk() {
        return jk;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getKeluhan() {
        return keluhan;
    }
    public String getNomor_handphone() {
        return nomor_handphone;
    }
}
