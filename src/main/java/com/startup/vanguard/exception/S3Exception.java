package com.startup.vanguard.exception;

public class S3Exception extends RuntimeException {
    public S3Exception(String filename) {
        super("Houve um erro ao carregar o arquivo: " + filename);
    }
}
