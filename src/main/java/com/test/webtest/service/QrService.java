package com.test.webtest.service;

import com.test.webtest.domain.QrDTO;
import crypto.SecureQrCryptoArray;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface QrService {
    byte[] createSecureQRCode(SecureQrCryptoArray arr, QrDTO qrDTO) throws IOException;
    void createQRImage(byte[] qr_byte_arr, String path);
    boolean isNull(QrDTO qrDTO);
}
