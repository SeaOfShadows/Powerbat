package org.powerbat;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.powerbat.configuration.Global;
import org.powerbat.configuration.Global.Paths;
import org.powerbat.gui.GUI;
import org.powerbat.gui.Splash;
import org.powerbat.methods.Updater;

import java.lang.reflect.InvocationTargetException;

/**
 * The boot class is responsible for basic loading for the client. Bringing all
 * the classes into unison, it effectively creates what is known as Powerbat.
 * Advanced technology to help you learn Java and fulfill what I like to know as
 * 'Good standing'. Helping others for free. Give what you can and take what you
 * must. From everything to the CustomClassLoader class to the Project class,
 * everything here was made for you, the user. I hope you have a great time
 * running this application. <br>
 * <br>
 * {@code
 * Powerbat - Program to help teach users Java. Copyright (C) 2013 Naux
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. <br>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details. <br>
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see http://opensource.org/licenses/gpl-3.0.html.}
 *
 * @author Naux
 * @version 1.0
 * @since 1.0
 */

public class Boot {

    /**
     * Nothing truly big to see here. Runs the application. Really should be
     * monitored but it isn't.
     *
     * @param args ignored. Or is it?
     * @since 1.0
     */

    public static void main(String[] args) {
        Paths.build();
        Global.loadImages();
        Splash.setStatus("Loading");
        final Splash splash = new Splash();
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    splash.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Updater.update();
        Splash.setStatus("Loading framework");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    Splash.setStatus("Building GUI");
                    new GUI();
                    splash.shouldDispose(true);
                    splash.dispose();
                    Splash.setStatus(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        });
    }
}
