package cleancode.minesweeper.tobe;

public enum GameStatus {
    WIN, LOSE, CONTINUE;

    private static GameStatus gameStatus = CONTINUE;

    public static void changeStatusToWin() {
        gameStatus = WIN;
    }

    public static void changeStatusToLose() {
        gameStatus = LOSE;
    }

    public static boolean isContinue() {
        return gameStatus == CONTINUE;
    }




}
