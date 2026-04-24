package com.example.conocimientosbasicos_andorid_studio.data.local.security;

public class EncryptedData {
    private byte[] iv;
    private byte[] ciphertext;

    public EncryptedData(byte[] iv, byte[] ciphertext) {
        this.iv = iv;
        this.ciphertext = ciphertext;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public byte[] getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(byte[] ciphertext) {
        this.ciphertext = ciphertext;
    }
}
