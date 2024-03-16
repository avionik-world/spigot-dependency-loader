package world.avionik.dependencyloader.plugin

import world.avionik.dependencyloader.plugin.command.DependenciesCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.net.URL
import java.net.URLClassLoader

/**
 * @author Niklas Nieberler
 */

class SpigotPlugin : JavaPlugin() {

    private val logger = Bukkit.getLogger()
    private val directory = File("dependencies")

    override fun onLoad() {
        checkFolderExists()

        val contextClassLoader = getContextClassLoader()
        if (contextClassLoader == null) {
            this.logger.warning("No dependencies could be loaded because the ClassLoader could not be serialised to a URLClassLoader!")
            return
        }

        getDependencyFiles()
            .map { it.toURI().toURL() }
            .forEach { addDependencyUrlToClassLoader(contextClassLoader, it) }
    }

    override fun onEnable() {
        val dependencyFiles = getDependencyFiles()
        val dependencySize = dependencyFiles.size
        this.logger.info("Loaded all $dependencySize dependencies")

        getCommand("dependencies")?.setExecutor(DependenciesCommand(dependencyFiles))
    }

    /**
     * @return the current classloader if it is an [URLClassLoader]
     */
    private fun getContextClassLoader(): URLClassLoader? {
        val contextClassLoader = Thread.currentThread().contextClassLoader
        if (contextClassLoader is URLClassLoader)
            return contextClassLoader
        return null
    }

    /**
     * Adds the url of a dependency to the current classLoader
     * @param urlClassLoader the current classLoader
     * @param url of dependency file
     */
    private fun addDependencyUrlToClassLoader(urlClassLoader: URLClassLoader, url: URL) {
        val urlClass: Class<*> = URLClassLoader::class.java
        val method = urlClass.getDeclaredMethod("addURL", URL::class.java)
        method.setAccessible(true)
        method.invoke(urlClassLoader, url)
    }

    /**
     * Creates the dependencies folder if it does not exist
     */
    private fun checkFolderExists() {
        if (!this.directory.exists()) {
            this.directory.mkdirs()
        }
    }

    /**
     * @return all jar files in the directories folder
     */
    private fun getDependencyFiles(): List<File> {
        return (this.directory.listFiles() ?: arrayOf())
            .filter { it.isFile }
            .filter { it.name.endsWith(".jar") }
    }

}