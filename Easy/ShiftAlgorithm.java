package encryptdecrypt;

public class ShiftAlgorithm implements CipherSelectionAlgorithm {
    private final int shiftRange = 'z' - 'a' + 1;
    @Override
    public String encryption(String s, int n) {
        String res = "";
        char[] msgCharArray = s.toCharArray();
        int msgLength = msgCharArray.length;
        char[] encryptedMessage = new char[msgLength];
        for (int i = 0; i < msgLength; i++) {
            char c = msgCharArray[i];
            if (Character.isLetter(c)) {
                char e = (char) (c + n);
                if ((Character.isLowerCase(c) && e > 'z') || (Character.isUpperCase(c) && e > 'Z')) {
                    e -= shiftRange;
                }
                if ((Character.isLowerCase(c) && e < 'a') || (Character.isUpperCase(c) && e < 'A')) {
                    e += shiftRange;
                }
                encryptedMessage[i] = e;
            } else {
                encryptedMessage[i] = c;
            }
        }
        for (char c:encryptedMessage) {
            res += c;
        }
        return res;
    }

    @Override
    public String decryption(String s, int n) {
        return encryption(s, -n);
    }
}
