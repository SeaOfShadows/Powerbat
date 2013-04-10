package org.powerbat.interfaces;

import org.powerbat.executor.Result;
import org.powerbat.projects.Project;

/**
 * All Runners must extend this class. It will handle all manifest properties
 * and project-specific builds.
 *
 * @author Naux
 * @since 1.0
 */

public abstract class Runner {

    private final Manifest properties = getClass().getAnnotation(Manifest.class);

    /**
     * Runs the user's code and is used to set up specific tests. This is the
     * only required method in a Runner.
     *
     * @since 1.0
     */
    public abstract Result[] getResults(Class<?> clazz);

    /**
     * Grabs the Runner version from the Manifest annotation. This is used in
     * the downloading and checking of files. Only truly used in the initial
     * boot of the application.
     *
     * @since 1.0
     */
    public double getVersion() {
        return properties.version();
    }

    /**
     * This is only ever overridden by any errors in the user's code. This is
     * not capable of handling HTML as of now.
     *
     * @since 1.0
     */

    public String getInstructions() {
        return properties.instructions();
    }

    /**
     * For the distribution into the respective folder during download, this is
     * needed to be known in able to for the application to process the Runner.
     * It needs to be valid, and one of the following:
     * <p/>
     * <tt>AP, Recursive, String, Array, Logic</tt>.
     *
     * @since 1.0
     */

    public String getCategory() {
        return properties.category();
    }

    /**
     * Returns the class name for skeleton construction. Soon to be used for
     * non-linear names between the Runner and the user's code.
     *
     * @see {@link Project#getSkeleton()}
     * @since 1.0
     */

    public String getClassName() {
        return properties.className();
    }

    /**
     * Temporary solution till a XML-based manifest set it introduced. Turns out
     * all of this is really just temporary. For now, it must be done very
     * precisely:
     * <p/>
     * {@code "int add(int a, int b)"}
     * <p/>
     * Which will return the following in the skeleton:
     * <p/>
     * {@code "public int add(int a, int b)"}
     *
     * @see {@link Project#getSkeleton()}
     * @since 1.0
     */

    public String getMethod() {
        return properties.method();
    }

}
