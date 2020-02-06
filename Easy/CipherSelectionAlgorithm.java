package encryptdecrypt;

public interface CipherSelectionAlgorithm {
    String encryption(String s, int n);
    String decryption(String s, int n);
}
