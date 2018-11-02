class Log {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    static final String logFile = ".\\log.txt";

    static void log(String message) {
        FileManager fileManager = new FileManager();
        if (Main.log) System.out.println(ANSI_PURPLE + message + ANSI_RESET);
        if (Main.logFile) fileManager.write(logFile, message);
    }
}
