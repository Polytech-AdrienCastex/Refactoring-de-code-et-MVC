package modele;

// package logo;

import left.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

	Source originale : J. Ferber - 1999-2001

			   Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/2001

**************************************************************************/

/** La classe Tortue qui se deplace en coordonnees polaires
**/

public class Tortue
{

	// Attributs statiques	
	protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
	
	// Attributs
	protected List<Segment> listSegments; // Trace de la tortue
	
	protected int x, y;	// Coordonnees de la tortue
	protected int dir;	// Direction de la tortue (angle en degres)
	protected boolean crayon; // par defaut on suppose qu'on dessine
	protected int coul;
        
        
        public List<Segment> getSegments()
        {
            return listSegments;
        }
	
	// Methodes
	public void setColor(int n) {coul = n;}
	public int getColor() {return coul;}

	public Tortue() { // FeuilleDessin f) {
		// feuille = f;
		// feuille.addTortue(this);	
		listSegments = new ArrayList<>();
		reset();
	}

	public void reset() {
		// on initialise la position de la tortue
		x = 0;
		y = 0;
		dir = -90;
		coul = 0;
		crayon = true;
		listSegments.clear();
  	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}
	

	protected Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.darkGray);
			case 4: return(Color.red);
			case 5: return(Color.green);
			case 6: return(Color.lightGray);
			case 7: return(Color.magenta);
			case 8: return(Color.orange);
			case 9: return(Color.gray);
			case 10: return(Color.pink);
			case 11: return(Color.yellow);
			default : return(Color.black);
		}
	}

	/** les procedures de base de fonctionnement de la tortue */

	// avancer de n pas
	public void avancer(int dist) {
		int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));
		
		if (crayon==true) {
			Segment seg = new Segment();
			
			seg.ptStart.x = x;
			seg.ptStart.y = y;
			seg.ptEnd.x = newX;
			seg.ptEnd.y = newY;
			seg.color = decodeColor(coul);
	
			listSegments.add(seg);
		}

		x = newX;
		y = newY;
	}

	// aller a droite
	public void droite(int ang) {
		dir = (dir + ang) % 360;
	}

	// aller a gauche
	public void gauche(int ang) {
		dir = (dir - ang) % 360;
	}

	// baisser le crayon pour dessiner
	public void baisserCrayon() {
		crayon = true;
	}

	// lever le crayon pour ne plus dessiner
	public void leverCrayon() {
		crayon = false;
	}

	// pour changer de couleur de dessin
	public void couleur(int n) {
		coul = n % 12;
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
	}

	/** quelques classiques */

	public void carre() {
		for (int i=0;i<4;i++) {
			avancer(100);
			droite(90);
		}
	}

	public void poly(int n, int a) {
		for (int j=0;j<a;j++) {
			avancer(n);
			droite(360/a);
		}
	}

	public void spiral(int n, int k, int a) {
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			avancer(n);
			droite(360/a);
			n = n+1;
		}
	}
}
