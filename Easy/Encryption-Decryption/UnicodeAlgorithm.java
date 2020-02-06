package encryptdecrypt;

public class UnicodeAlgorithm implements CipherSelectionAlgorithm {

    @Override
    public String encryption(String s, int n) {
        char[] msgCharArray = s.toCharArray();
        int msgLength = msgCharArray.length;
        char[] encryptedMessage = new char[msgLength];
        for (int i = 0; i < msgLength; i++) {
            encryptedMessage[i] = (char) (msgCharArray[i] + n);
        }
        return new String(encryptedMessage);
    }

    @Override
    public String decryption(String s, int n) {
        return encryption(s, -n);
    }
}
