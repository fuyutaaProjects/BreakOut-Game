package org.openjfx;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * La classe Dev_niveau permet de créer et de manipuler des niveaux personnalisés pour le jeu.
 * Elle hérite de la classe BreakOut et fournit des fonctionnalités pour ajouter, supprimer et manipuler des polygones.
 */
public class Dev_niveau extends BreakOut {

    private Pane pane = new Pane();
    private List<Polygon> polys = new ArrayList<>();

    /**
     * Constructeur de la classe Dev_niveau.
     * Initialise la scène et ajoute un premier polygone.
     */
    public Dev_niveau() {
        init_scene();
    }

    /**
     * Initialise la scène avec un panneau et un premier polygone.
     * Configure les événements de clic de souris et de pression de touche.
     */
    public void init_scene() {
        Scene scene = new Scene(pane, width, height);
        Tstage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/org/openjfx/styles.css").toExternalForm());

        addPoly();

        scene.setOnMouseClicked(e -> click(e.getX(),e.getY()));
        scene.setOnKeyPressed(event->keyPressed(event.getCode().toString()));
    }

    /**
     * Retourne en arrière en supprimant les derniers points ajoutés au polygone actuel.
     * Si le polygone actuel n'a plus de points, il est supprimé de la liste des polygones.
     */
    public void goBack(){
        Polygon poly = polys.get(polys.size() - 1);

        if (poly.getPoints().size() >= 2) {
            poly.getPoints().remove(poly.getPoints().size() -2 ,poly.getPoints().size());
        }

        if (poly.getPoints().size()==0){
            removePoly();
        }
    }

    /**
     * Supprime le dernier polygone de la liste des polygones.
     * Si la liste des polygones est vide, un nouveau polygone est ajouté.
     */
    public void removePoly(){
        if (!polys.isEmpty()) {
            polys.remove(polys.size() - 1); // Suppression du dernier élément
            pane.getChildren().remove(pane.getChildren().size() - 1);
            if (polys.isEmpty()){
                addPoly();
            }
        }
    }

    /**
     * Ajoute un nouveau polygone à la liste des polygones.
     * Le nouveau polygone est ajouté au panneau et à la liste des polygones.
     */
    public void addPoly(){
        Polygon poly= new Polygon();
        poly.setFill(Color.LIGHTBLUE);
        poly.setStroke(Color.DARKBLUE); 

        polys.add(poly);
        pane.getChildren().add(poly);
    }

 /**
     * Gère les pressions de touches pour ajouter ou supprimer des polygones et des points.
     * 
     * @param key La touche pressée.
     */

    public void keyPressed(String event){
        if (event.equals("N")){
            addPoly();
        }
        if (event.equals("R")){
            removePoly();
        }
        if (event.equals("B")){
            goBack();
        }

        affichePoly();
    }

     /**
     * Ajoute un point au polygone actuel.
     * 
     * @param x La coordonnée X du point à ajouter.
     * @param y La coordonnée Y du point à ajouter.
     */
    public void addToPoly(double X, double Y){
        polys.get(polys.size() - 1).getPoints().addAll(X, Y);
    }

    /**
     * Gère les clics de souris en ajoutant des points au polygone actuel.
     * 
     * @param x La coordonnée X du clic de souris.
     * @param y La coordonnée Y du clic de souris.
     */
    private void click(double X, double Y){
        addToPoly(X, Y);
        affichePoly();
    }

    private String poly_toString(Polygon poly){
        String str="{";
        ObservableList<Double> points = poly.getPoints();
        for (int i=0; i<points.size();i+=2){
            str+="\n\t{"+points.get(i)+","+points.get(i+1)+"}"+",";
            
        }
        if (str.length() > 1) {
            str= str.substring(0, str.length() - 1); 
        }
        str+="\n}";
        return str;
    }
    private void affichePoly(){
        System.out.println("---------------------\n\n");
        for (int i=0;i<polys.size();i++){
            System.out.println( "points="+poly_toString(polys.get(i))+";");
            System.out.println("super.terrain.add(new Briques_personnalise(points,40,3,null));\n");
        }
    }




    public void loop (){
        
    }

    public Pane getPane() {
        return pane;
    }
/**
     * Obtient la liste des polygones.
     * 
     * @return La liste des polygones.
     */
    public List<Polygon> getPolys() {
        return polys;
    }
}
