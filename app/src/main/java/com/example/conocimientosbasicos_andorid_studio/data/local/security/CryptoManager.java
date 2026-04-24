package com.example.conocimientosbasicos_andorid_studio.data.local.security;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class CryptoManager {

    @Inject
    public CryptoManager() {
    }

    private void generateAndStoreKey(String alias) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");

            KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT
            ).setKeySize(256)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setDigests(KeyProperties.DIGEST_SHA256)
                .build();

            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey getKeyFromKeystore(String alias) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            if (!keyStore.containsAlias(alias)) {
                //lazy init
                generateAndStoreKey(alias);
            }

            KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null);

            return keyEntry.getSecretKey();

        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException |
                 UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        }
    }

    public EncryptedData encryptedData(String plainText, String alias) {
        try {
            SecretKey secretKey = getKeyFromKeystore(alias);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] iv = cipher.getIV();

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return new EncryptedData(iv, encryptedBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptData(EncryptedData encryptedData, String alias) throws RuntimeException {
        try {
            SecretKey secretKey = getKeyFromKeystore(alias);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(
                    128,
                    encryptedData.getIv()
            );

            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] decryptedData = cipher.doFinal(encryptedData.getCiphertext());

            return new String(decryptedData, StandardCharsets.UTF_8);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
