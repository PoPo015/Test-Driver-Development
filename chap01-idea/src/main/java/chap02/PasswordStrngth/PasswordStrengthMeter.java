package chap02.PasswordStrngth;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {

        int metCounts = getMetCounts(s);

        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        if(metCounts <= 1) return PasswordStrength.WEAK;
        if(metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
/*      중복 코드 리팩토링
        if(lengthEnough && !conatinsNum && !containsUpp) return PasswordStrength.WEAK;
        if(!lengthEnough && conatinsNum && !containsUpp) return PasswordStrength.WEAK;
        if(!lengthEnough && !conatinsNum && containsUpp) return PasswordStrength.WEAK;
*/

/*
        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!conatinsNum) return PasswordStrength.NORMAL;
        if (!containsUpp) return PasswordStrength.NORMAL;
*/

    }

    private int getMetCounts(String s) {
        boolean lengthEnough = s.length() >= 8;
        boolean conatinsNum = meetsContainingNumberCriteria(s);
        boolean containsUpp = meetsContatiningUppercaseCriteria(s);
        int metCounts = 0;
        if(lengthEnough) metCounts++;
        if(conatinsNum) metCounts++;
        if(containsUpp) metCounts++;
        return metCounts;
    }

    private boolean meetsContatiningUppercaseCriteria(String s) {
        boolean containsUpp = false;
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }


    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

}
