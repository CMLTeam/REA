package com.cmlteam.util;

import java.io.File;

public class Util {
    private Util() {
    }

    public static String renderFileSize(File file) {
        return renderFileSize(file.length());
    }

    public static String renderFileSize(long bytes) {
        return humanReadableByteCount(bytes, false).replace("i", "");
    }

    /**
     * http://stackoverflow.com/a/3758880/104522
     */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static String renderDuration(long startMillis) {
        return renderInterval(System.currentTimeMillis() - startMillis);
    }

    public static String renderInterval(long deltaMillis) {
        float deltaSec = deltaMillis / 1000f;
        int deltaMin = 0;
        int deltaHr = 0;
        int deltaDay = 0;

        if (deltaSec >= 60) {
            deltaMin = (int) (deltaSec / 60);
            deltaSec = deltaSec - (deltaMin * 60);
        }

        if (deltaMin >= 60) {
            deltaHr = deltaMin / 60;
            deltaMin = deltaMin - (deltaHr * 60);
        }

        if (deltaHr >= 24) {
            deltaDay = deltaHr / 24;
            deltaHr = deltaHr - (deltaDay * 24);
        }

        String dayS = deltaDay > 0 ? deltaDay + " d" : null;
        String hrS = deltaHr > 0 ? deltaHr + " h" : null;
        String minS = deltaMin > 0 ? deltaMin + " m" : null;
        String secS = deltaSec > 0 ? deltaSec + " s" : null;
        String secSI = deltaSec > 0 ? ((int) deltaSec) + " s" : null;

        return dayS != null
                ? dayS + " " + hrS
                : deltaHr > 0
                ? hrS + " " + minS
                : deltaMin > 0
                ? minS + " " + secSI
                : deltaSec > 0
                ? secS
                : "0 s";
    }

    public static String trim(String str, int maxLen) {
        if (str == null)
            return null;

        if (str.length() <= maxLen)
            return str;

        return str.substring(0, maxLen) + "...";
    }

    public static int safeToInt(Object intCandidate) {
        return safeToInt(intCandidate, 0);
    }

    public static int safeToInt(Object intCandidate, int defaultVal) {
        if (intCandidate instanceof Number) {
            return ((Number) intCandidate).intValue();
        }

        if (intCandidate instanceof String) {
            try {
                return Integer.parseInt((String) intCandidate);
            } catch (NumberFormatException e) {
                return defaultVal;
            }
        }

        return defaultVal;
    }

    public static long safeToLong(Object longCandidate) {
        return safeToLong(longCandidate, 0);
    }

    public static long safeToLong(Object longCandidate, long defaultVal) {
        if (longCandidate instanceof Number) {
            return ((Number) longCandidate).longValue();
        }

        if (longCandidate instanceof String) {
            try {
                return Long.parseLong((String) longCandidate);
            } catch (NumberFormatException e) {
                return defaultVal;
            }
        }

        return defaultVal;
    }
}
