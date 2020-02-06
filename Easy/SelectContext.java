package encryptdecrypt;

class SelectContext {
    private CipherSelectionAlgorithm algorithm;

    public void setAlgorithm(CipherSelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String selectEncryption(String s, int n) {
        return  this.algorithm.encryption(s,n);
    }
    public String selectDecryption(String s, int n) {
        return  this.algorithm.decryption(s,n);
    }
}
