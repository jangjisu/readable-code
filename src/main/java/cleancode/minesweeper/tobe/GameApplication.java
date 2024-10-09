package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    //메인 함수는 프로그램을 실행하는 역할만 하도록 하고,
    //지뢰찾기라는 게임을 메인함수에서 실행 시켜주도록 책임 분리
    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
                new Advanced(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );

        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initalize();
        minesweeper.run();
    }

}
