package com.example.conocimientosbasicos_andorid_studio.data.local.security;

import android.content.SharedPreferences;
import android.util.Base64;

import javax.inject.Inject;

public class SecurePreferences {
    private final SharedPreferences preferences;
    private final CryptoManager cryptoManager;
    private static final String ALIAS = "mi_llave_secreta";

    @Inject
    public SecurePreferences(SharedPreferences preferences, CryptoManager cryptoManager) {
        this.preferences = preferences;
        this.cryptoManager = cryptoManager;
    }

    public void saveEncryptedString(String key, String plainText) {
        EncryptedData data = cryptoManager.encryptedData(plainText, ALIAS);

        // Convertimos bytes a String legible (Base64)
        String ivBase64 = Base64.encodeToString(data.getIv(), Base64.DEFAULT);
        String encryptedBase64 = Base64.encodeToString(data.getCiphertext(), Base64.DEFAULT);

        //Guardamos ambos
        preferences.edit()
                .putString(key + "_iv", ivBase64)
                .putString(key + "_data", encryptedBase64)
                .apply();
    }

    public String getEncryptedString(String key) {
        // 1. Recuperamos los Strings en Base64 que guardamos en las SharedPreferences
        String ivBase64 = preferences.getString(key + "_iv", null);
        String dataBase64 = preferences.getString(key + "_data", null);

        // 2. Si falta alguno, es que no hay nada guardado
        if (ivBase64 == null || dataBase64 == null) {
            return null;
        }

        // 3. Convertimos de vuelta: de "Texto Base64" a "Arrays de bytes"
        byte[] iv = Base64.decode(ivBase64, Base64.DEFAULT);
        byte[] ciphertext = Base64.decode(dataBase64, Base64.DEFAULT);

        // 4. Empaquetamos en el objeto que entiende tu CryptoManager
        EncryptedData encryptedData = new EncryptedData(iv, ciphertext);

        // 5. Usamos el CryptoManager para descifrar y devolver el String original
        return cryptoManager.decryptData(encryptedData, ALIAS);
    }
}
