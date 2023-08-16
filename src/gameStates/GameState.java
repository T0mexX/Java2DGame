package gameStates;

public enum GameState {
	MENU,
	GAME_ACTIVE,
	OPTIONS,
	QUIT;
	public static GameState currentState = MENU;
}
