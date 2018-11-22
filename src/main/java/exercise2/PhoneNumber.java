package exercise2;

import java.io.Serializable;

public class PhoneNumber implements Serializable {
	private final String number;

	public PhoneNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}
}
/*les 2 ajouts à la classe :
import de la librairie seriazable (indispensable pour rendre l'objet écrivable
ajout de implements seriasable (pour préciser ce qui est au dessus,
seriazable est une interface de laquelle tout élément qu'on veut écrire sur
un fichier doit être implémentée
donc ici si on veut écrire un phone number sur le fichier, faut que la classe
phonenumer soit classé comme seriazable ! */
