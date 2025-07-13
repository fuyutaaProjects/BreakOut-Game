package org.openjfx.Game_ressources;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * La classe Audio gère le chargement, la lecture, la pause, et la répétition des fichiers audio.
 * Les fichiers audio doivent être au format `.wav` et placés dans `src/main/resources/`.
 */
public class Audio {
    private static boolean audio_disabled_for_test = true; // true en cas normal, false si en phase de test
    private Clip audio; // L'objet Clip utilisé pour jouer l'audio
    private boolean isplaying; // Indique si l'audio est en cours de lecture

    /**
     * Constructeur par défaut de la classe Audio.
     * Initialise l'objet audio et définit l'état de lecture sur faux.
     */
    public Audio() {
        audio = null;
        isplaying = false;
    }

    /**
     * Vérifie si l'audio est en cours de lecture.
     * 
     * @return true si l'audio est chargé, false si il est null.
     */
    public boolean audio(){
        if(audio == null){
            return false;
        }
        else return true;
    }

    /**
     * Vérifie si l'audio est en cours de lecture.
     * 
     * @return true si l'audio est en lecture, false sinon.
     */
    public boolean isplaying() {
        return isplaying;
    }

    /**
     * Charge le fichier audio spécifié dans l'objet Clip.
     * Le fichier doit être placé dans `src/main/resources/` et être au format `.wav` et d'autre spécifités mais je m'en occupe donc si vous avez un soucis avec, envoyer moi un DM(beelz).
     * 
     * @param fileName le nom du fichier audio à charger (avec l'extension).
     */
    public void load(String fileName) {
        if (audio_disabled_for_test) {
            try {
                URL soundURL = getClass().getResource("/" + fileName);
                AudioInputStream audioIS = AudioSystem.getAudioInputStream(soundURL);
    
                // Check if a line is available
                if (AudioSystem.isLineSupported(new Line.Info(Clip.class))) {
                    audio = AudioSystem.getClip();
                    audio.open(audioIS);
                } else {
                    System.out.println("No audio output available, Disabling audio");
                    audio_disabled_for_test = false;
                }
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                System.out.println("Audio disabled");
                audio_disabled_for_test = false;
            }
        }
    }

    /**
     * Joue l'audio depuis le début.
     * Si l'audio est déjà en cours de lecture, il recommence depuis le début.
     */
    public void play() {
        if (audio != null && audio_disabled_for_test) {
            audio.setFramePosition(0); // Recommence depuis le début
            audio.start();
            isplaying = true;
        }
    }

    /**
     * Met l'audio en boucle de manière continue.
     * Cette méthode nécessite que l'audio ait déjà été chargé.
     */
    public void loop() {
        if (audio != null && audio_disabled_for_test) {
            audio.loop(audio.LOOP_CONTINUOUSLY);
            isplaying = true;
        }
    }

    /**
     * Arrête temporairement l'audio.
     * L'audio peut être repris à partir de la position actuelle.
     */
    public void stop() {
        if (audio != null && audio.isRunning() && audio_disabled_for_test) {
            audio.stop();
        }
    }

    /**
     * Ferme l'audio et libère les ressources associées.
     * Après cet appel, l'audio ne pourra plus être utilisé sans le recharger.
     */
    public void close() {
        if (audio != null && audio_disabled_for_test) {
            audio.close();
        }
    }

    /**
     * Met à jour la valeur audio_diasbled_for_test, comme son nom l'indique
     * Cela sert seulement dans le cas des tests qui crash si audio est actif
     * La méthode est statique pour permettre de désactiver toutes instances d'audio en une seule ligne
     * 
     * @param bool un boolean, true dans le cas normal, false quand les tests javafx sont en cours
     */
    public static void set_audio_disabled_for_test(boolean bool){
        audio_disabled_for_test = bool;
    }
}
