package com.web.servlets;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.GameContextManagement;

/**
 * Implémente les régles du jeu
 * 
 * @author Tarik BOUDAA
 */
@WebServlet("/back/GameServlet")
public class GameServlet extends HttpServlet {


	/**
	 * Méthode permettant à un utilisateur de jouer
	 * 
	 */
	protected void play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			// On récupére la session de l'utilisateur en cours
			HttpSession session = request.getSession();
	
			// On récupére de la session, les informations du joueur en cours
			User user = (User) session.getAttribute("user");
	
			// Pour gérer les données de l'application dans le ServletContext
			GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());
	
			// Cette objet est déjà inséré dans la session au moment de login
			GameState gameSate = (GameState) session.getAttribute("gameState");

			// Lancer un dé (générer un nombre aléatoire dans l'intervalle 1,6)
			int resultat = new Random().nextInt(1, 7);
			System.out.print("paramTest: ");
			// we get dice number from the user using the parameter throw
			int diceNumber = Integer.valueOf(request.getParameter("throw"));
			System.out.println(diceNumber);
			
			// On ajoute un message indiquant le résultat de lancée
			gameSate.addMessage(new Message(String.valueOf(resultat), Message.INFO));
			
			
			if (gameSate.isGameOver()) {
				// On ajoute un message d'information
				gameSate.addMessage(new Message("Game Over", Message.INFO));

				// On vérifie s'il faut mettre à jour le meilleur score pour ce joueur
				if (user.getScore() > user.getBestScore()) {

					// Si oui alors mise à jour des données dans la session
					user.setBestScore(user.getScore());

					// Mise à jour des données dans le contexte de l'application
					gameContext.updateScore(user);
				}
				// On indique que le jeu est terminé
				gameSate.setGameOver(true);

			
			}
			
			// We check that current dice number not inserted before to store it in the hashmap
			if(gameSate.getDiceList().get(diceNumber) == null ) {
				gameSate.getDiceList().put(diceNumber, resultat);
				System.out.println("the current value from the hashmap: "+gameSate.getDiceList().get(diceNumber));
			}else {
				gameSate.addMessage(new Message("The dice has been already played!", Message.WARN));
				user.setScore((-1));
				gameSate.setGameOver(true);
				getServletContext().getRequestDispatcher("/WEB-INF/vues/back/finalScore.jsp").forward(request, response);
				return;
			}
			

			if(gameSate.getDiceList().get(2) != null) {
				if(gameSate.getDiceList().get(2) == 6) {
					System.out.println("Sorry your score is null 0");
					user.setScore(0);
					// On indique que le jeu est terminé
					gameSate.setGameOver(true);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/finalScore.jsp").forward(request, response);
					// Fin
					return;
				}
			}
			if(gameSate.getDiceList().get(1) != null) {
				if(gameSate.getDiceList().get(1) == 6) {
					System.out.println("Sorry your score is null 1");
					user.setScore(0);
					// On indique que le jeu est terminé
					gameSate.setGameOver(true);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/finalScore.jsp").forward(request, response);
					// Fin
					return;
				}
			}
			if(!(gameSate.getDiceList().get(1) == null) && (gameSate.getDiceList().get(2) != null)) {		
				if(gameSate.getDiceList().get(1) >= gameSate.getDiceList().get(2)) {
					// On indique que le jeu est terminé
					gameSate.setGameOver(true);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/finalScore.jsp").forward(request, response);
					// Fin
					return;
				}
			}
			
			if(gameSate.getDiceList().get(1)!= null && gameSate.getDiceList().get(2)!= null && gameSate.getDiceList().get(3)!= null) {
				
				if((gameSate.getDiceList().get(1) < gameSate.getDiceList().get(2)) && (gameSate.getDiceList().get(2)<gameSate.getDiceList().get(3))) {
					// Calculate the sum of all values
			        int sum = 0;
			        for (int value : gameSate.getDiceList().values()) {
			            sum += value;
			        }
					user.setScore(sum);
					System.out.println("your score is :"+user.getScore());
					
					gameSate.addMessage(new Message(" Gagné !", Message.INFO));
					// On indique que le jeu est terminé
					gameSate.setGameOver(true);
				}
				else {
					System.out.println("Sorry your score is null 2");
					user.setScore(0);
					// On indique que le jeu est terminé
					gameSate.setGameOver(true);
				}
				getServletContext().getRequestDispatcher("/WEB-INF/vues/back/finalScore.jsp").forward(request, response);
				// Fin
				return;
			}
	
			// On redirige vers la vue par une redirection au niveau du serveur
			getServletContext().getRequestDispatcher("/WEB-INF/vues/back/gamerHome.jsp").forward(request, response);
		
			// On arrete l'execution
			return;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		play(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		play(request, response);

	}

}
