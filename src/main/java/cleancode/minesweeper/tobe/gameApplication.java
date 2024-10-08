package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.*;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class gameApplication {

    //메인 함수는 프로그램을 실행하는 역할만 하도록 하고,
    //지뢰찾기라는 게임을 메인함수에서 실행 시켜주도록 책임 분리
    public static void main(String[] args) {
        GameLevel gameLevel = new Advanced();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initalize();
        minesweeper.run();
    }

}
