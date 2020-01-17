package nwawsoft.pwng.model;

import nwawsoft.pwng.exceptions.*;
import nwawsoft.pwng.model.characterset.*;
import nwawsoft.pwng.model.language.*;

import java.io.*;

public class Settings {
    private Language l;
    private CharacterSet cs;

    // ToDo: Doc
    public Settings() {
        if (configFileFound()) {
            if (configFileValid()) {
                load();
            }
        }
    }

    // ToDo: Doc
    public static void save(final String language, final String charSet) {
        try {
            File d = new File(System.getProperty("user.home") + "/.pwng");
            boolean dirCreated = d.mkdir();
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("LANG=" + language + "\n");
            bw.write("CHARSET=" + charSet + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether a config file is found at the default path ($user.home$/.pwng/settings.ini).
     * @return true if file was found. Else false.
     */
    public static boolean configFileFound() {
        File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
        return f.exists();
    }

    // ToDo: Doc
    public static boolean configFileValid() {
        int requirement = 2;
        int current = 0;
        try {
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.contains("=")) {
                    if (currentLine.startsWith("LANG")) {
                        // ToDo: Also verify actual content
                        current++;
                    }
                    if (currentLine.startsWith("CHARSET")) {
                        // ToDo: Also verify actual content
                        current++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return current == requirement;
    }

    // ToDo: Doc
    public void load() {
        try {
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.startsWith("LANG")) {
                    l = Languagizer.toLanguage(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                    System.out.println(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                } else if (currentLine.startsWith("CHARSET")) {
                    cs = CharacterSetizer.toCharacterSet(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                }
            }
        } catch (IOException | UnknownLanguageException | UnhandledCharacterSetException e) {
            e.printStackTrace();
        }
    }

    public Language getLanguage() {
        return l;
    }

    public CharacterSet getCharacterSet() {
        return cs;
    }
}