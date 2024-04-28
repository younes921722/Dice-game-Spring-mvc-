package com.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


/**
 * Encapsule l'état d'une partie de jeu
 * 
 * @author T.BOUDAA
 *
 */
public class GameState {

	private User user;

	private boolean gameOver = false;

	private List<Message> messages = new ArrayList<Message>();
	private HashMap<Integer,Integer> diceList = new HashMap<Integer,Integer>();

	public void reinit() {
		// Empty the HashMap
        diceList.clear();
		gameOver = false;
		messages = new ArrayList<Message>();
		user.setScore(0);
		user.setCompteurLancer(0);

	}

	public String toString() {
		return "GameState [Score=" + user.getScore() + ", nombre lancers=" + user.getCompteurLancer() + ", messages="
				+ messages + "]";
	}

	public void addMessage(Message ms) {
		messages.add(ms);
	}

	public GameState(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public HashMap<Integer, Integer> getDiceList() {
		return diceList;
	}

	public void setDiceList(HashMap<Integer, Integer> diceList) {
		this.diceList = diceList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		// Empty the HashMap
        diceList.clear();
		this.gameOver = gameOver;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
