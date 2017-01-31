package Monopoly;

public class CarteAction {
	
	private int nbActionMax = 7;
	private int nbAction=0;
	
	public void doAction(){
		switch (nbAction) {
		case 0:
			Window.addConsoleText("Vous devez payer les frais de scolarité à l'EPSI 7500€");
			Controller.addCagnotteToParcGratuit(Tours.getJoueur().depenseCagnotte(7500));
			break;
			
		case 1:
			Window.addConsoleText("Erreur de la banque en votre faveur, recevez 20000€");
			Tours.getJoueur().encaisseCagnotte(20000);
			break;
			
		case 2:
			Window.addConsoleText("Allez Directement en prison");
			Tours.getJoueur().setPosition(30);
			Controller.controllerPosition(Tours.getJoueur());
			break;
			
		case 3:
			Window.addConsoleText("Rendez-vous à la rue de la Paix");
			Tours.getJoueur().setPosition(39);
			Controller.controllerPosition(Tours.getJoueur());
			break;
			
		case 4:
			Window.addConsoleText("Rendez-vous à la case Départ");
			Tours.getJoueur().setPosition(0);
			Controller.controllerPosition(Tours.getJoueur());
			break;
			
		case 5:
			Window.addConsoleText("Vous avez gagné le 2nd prix de beauté, recevez 10000€");
			Tours.getJoueur().encaisseCagnotte(10000);
			break;
			
		case 6:
			Window.addConsoleText("Amende pour excés de vitesse payez 2500€");
			Controller.addCagnotteToParcGratuit(Tours.getJoueur().depenseCagnotte(2500));
			break;
		
		case 7:
			double impotRevenu = Tours.getJoueur().getCagnotte()*0.1;
			Window.addConsoleText("Impots sur le revenu, vous devez payé 10% de votre cagnotte soit"+((int)Tours.getJoueur().getCagnotte()*0.1)+"€");
			Controller.addCagnotteToParcGratuit(Tours.getJoueur().depenseCagnotte((int) impotRevenu));
			break;
			
		}
		
		this.randomNextNbAction();
	}
	
	public void randomNextNbAction(){
		int newNbAction;
		do{
			newNbAction = Des.aleatoirNumber(0, nbActionMax);
		}while(newNbAction == nbAction);
		nbAction = newNbAction;
	}
	
}
