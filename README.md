## Spigot Dependency Loader ☕
The Spigot Dependency Loader is a useful plugin for Spigot and Paper Server that allows you to load external dependencies in the form of JAR files into a special folder called `dependencies`. These dependencies are then added to the current classloader when the server is started.

#### Important notes
It is **extremely important** to add the JVM argument `--add-opens java.base/java.net=ALL-UNNAMED`. If this is not done, the plugin may not work properly.

##### Supported Minecraft versions: 1.20 to 1.20.4
