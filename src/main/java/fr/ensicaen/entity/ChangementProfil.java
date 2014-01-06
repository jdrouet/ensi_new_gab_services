package fr.ensicaen.entity;
import javax.persistence.*;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */

@Table(name = "changementProfil")
public class ChangementProfil extends Action{
	@OneToOne
	Profil ancienProfil;
	
	@OneToOne
	Profil nouveauProfil;

	public Profil getAncienProfil() {
		return ancienProfil;
	}

	public void setAncienProfil(Profil ancienProfil) {
		this.ancienProfil = ancienProfil;
	}

	public Profil getNouveauProfil() {
		return nouveauProfil;
	}

	public void setNouveauProfil(Profil nouveauProfil) {
		this.nouveauProfil = nouveauProfil;
	}	
	
}
