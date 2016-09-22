package com.solfacell.model;

import java.util.List;

/**
 * Created by Ratri on 9/19/2016.
 */
public class PriceList {
    private List<Voucher> voucher;

    public List<Voucher> getVoucher() {
        return voucher;
    }

    public void setVoucher(List<Voucher> voucher) {
        this.voucher = voucher;
    }

    public class Voucher{
        public int id_nominal;
        public String nama;
        public String harga;
        public int id_voucher;

        public int getId_nominal() {
            return id_nominal;
        }

        public void setId_nominal(int id_nominal) {
            this.id_nominal = id_nominal;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public int getId_voucher() {
            return id_voucher;
        }

        public void setId_voucher(int id_voucher) {
            this.id_voucher = id_voucher;
        }
    }

}