package appendix_C_Test;

public interface GameNumGen {
    String generate(GameLevel level);

    void generate(Class<GameLevel> gameLevelClass);


}
