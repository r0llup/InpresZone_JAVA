/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inpreszone_java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;

/**
 *
 * @author Sh1fT
 */

public class Utils {
    /**
     * Permet de récupérer le BLOB correspondant à un certain _id
     * @param couchDbConnector
     * @param _id
     * @return AttachmentInputStream
     */
    protected static AttachmentInputStream getBlob(CouchDbConnector couchDbConnector, String _id) {
        try {
            AttachmentInputStream data = couchDbConnector.getAttachment(_id, "image.jpg");
            return data;
        } catch (DocumentNotFoundException ex) {
            return null;
        }
    }

    /**
     * Corrige le problème de parsing (ex : Anglais,) des langues
     * @param p 
     */
    protected static void fixSpeechComa(Product p) {
        if (p.getLanguages() != null) {
            LinkedList<String> languages = new LinkedList<>();
            for (String l : p.getLanguages()) {
                if (l.contains(","))
                    languages.add(l.substring(0, l.indexOf(",")));
                else
                    languages.add(l);
            }
            p.setLanguages(languages);
        }
        if (p.getLanguage() != null) {
            if (p.getLanguage().contains(","))
                p.setLanguage(p.getLanguage().substring(0, p.getLanguage().indexOf(",")));
        }
    }

    /**
     * Corrige le problème de parsing (ex : Anglais,) des sous-titres
     * @param p 
     */
    protected static void fixSubtitleComa(Product p) {
        if (p.getSubtitles() != null) {
            LinkedList<String> subtitles = new LinkedList<>();
            for (String l : p.getSubtitles()) {
                if (l.contains(","))
                    subtitles.add(l.substring(0, l.indexOf(",")));
                else
                    subtitles.add(l);
            }
            p.setSubtitles(subtitles);
        }
    }

    /**
     * Récupère les différentes langues de la base CouchDB
     * @param productList
     * @return List<String>
     */
    protected static List<String> fetchSpeech(HashSet<Product> productList) {
        LinkedList<String> languageList = new LinkedList<>();
        if (productList != null) {
            for (Product p : productList) {
                if (p.getLanguages() != null) {
                    Utils.fixSpeechComa(p);
                    for (String l : p.getLanguages()) {
                        if (!languageList.contains(l))
                            languageList.add(l);
                    }
                }
                if ( p.getLanguage() != null) {
                    Utils.fixSpeechComa(p);
                    if (!languageList.contains(p.getLanguage()))
                        languageList.add(p.getLanguage());
                }
                if (p.getSubtitles() != null) {
                    Utils.fixSubtitleComa(p);
                    for (String s : p.getSubtitles()) {
                        if (!languageList.contains(s))
                            languageList.add(s);
                    }
                }
            }
        }
        return languageList;
    }

    /**
     * Transforme une chaîne de caractères en une date suivant un certain format
     * @param date
     * @param dateFormat
     * @param locale
     * @return java.sql.Date
     */
    protected static java.sql.Date stringToDate(String date, int dateFormat, Locale locale) {
        DateFormat df = DateFormat.getDateInstance(dateFormat, locale);
        try {
            return new java.sql.Date(df.parse(date).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Transforme une chaîne de caractères en une date suivant un certain format
     * @param date
     * @param dateFormat
     * @return java.sql.Date
     */
    protected static java.sql.Date stringToDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Transforme une chaîne de caractères en une date suivant un certain format
     * @param date
     * @param dateFormat
     * @param locale
     * @return java.sql.Date
     */
    protected static java.sql.Date stringToDate(String date, String dateFormat, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
        try {
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Retourne une liste de chaînes de caractères séparées par une virgule
     * @param array
     * @return String
     */
    protected static String arrayToString(List<String> array) {
        if (array != null) {
            String str = "";
            for (String s : array) {
                if (str.compareTo("") == 0)
                    str = s + ",";
                else
                    str += s + ",";
            }
            return str;
        }
        return null;
    }

    /**
     * Fournit un tableau de chaînes de caractères ne comportant pas de doublons
     * @param array_in
     * @return List<String>
     */
    protected static List<String> fixDupValues(List<String> array_in) {
        if (array_in != null) {
            LinkedList<String> array_out = new LinkedList<>();
            for (String s : array_in) {
                if (!array_out.contains(s))
                    array_out.add(s);
            }
            return array_out;
        }
        return null;
    }
}